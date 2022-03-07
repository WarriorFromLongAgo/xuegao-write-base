package com.xuegao.framework.context;

import framework.annotation.MyAutowired;
import framework.annotation.MyController;
import framework.annotation.MyService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * 应用上下文 -- 提供bean注册、依赖注入、配置解析功能
 *
 * @author JiaoFanTing
 **/
public class ApplicationContext {
    /**
     * 存放示例对象的map
     */
    private Map<String, Object> instanceMapping = new HashMap<>();
    /**
     * 存放class信息的list
     */
    private List<String> classCache = new ArrayList<>();
    /**
     * 读取配置文件的properties
     */
    private Properties config = new Properties();

    /**
     * 构造方法：
     * 传入一个配置文件路径，对IOC进行初始化
     *
     * @author JiaoFanTing
     */
    public ApplicationContext(String location) {
        InputStream is;
        try {
            // 1. 载入配置文件
//            InputStream is = this.getClass().getClassLoader().getResourceAsStream(location);
            is = new FileInputStream("D:\\workspace\\easy-springmvc\\src\\main\\resources\\application.properties");
            config.load(is);
            // 2. 获取配置属性-- 扫描的包
            String packageName = config.getProperty("packageScan");
            // 3. 注册
            doRegister(packageName);
            // 4. 初始化IOC
            doCreateBean();
            // 5. 依赖注入
            populate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("IOC 容器已经初始化");
    }

    /**
     * 依赖注入
     *
     * @author JiaoFanTing
     **/
    private void populate() {
        // 1. 判断IOC容器是否为空
        if (instanceMapping.isEmpty()) {
            return;
        }
        // 2. 遍历 每个bean的字段
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            // 获取每个bean的fields
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 遍历判断是否需要依赖注入
            for (Field field : fields) {

                if (field.isAnnotationPresent(MyAutowired.class)) {
                    MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                    String id = autowired.value().trim();
                    if (id.equals("")) {
                        // 如果用户没有自定义bean名， 则默认用类型来注入
                        id = field.getType().getName();
                    }
                    field.setAccessible(true);

                    try {
                        // entry.getValue() 代表的就是当前的类对象
                        field.set(entry.getValue(), instanceMapping.get(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 创建bean
     *
     * @author JiaoFanTing
     **/
    private void doCreateBean() {
        // 1. 检查是否有类信息注册缓存
        if (classCache == null) {
            return;
        }
        // 2. 遍历classCache 并创建实例 存放到 instanceMapping
        try {
            for (String className : classCache) {
                // 反射加载类
                Class<?> clazz = Class.forName(className);
                // 加了需要加入IOC容器的注解，才进行初始化
                if (clazz.isAnnotationPresent(MyController.class)) {
                    // 类的首字母小写，并存入 instanceMapping
                    String id = firstCharToLower(clazz.getSimpleName());
                    instanceMapping.put(id, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    // service注解就有了 用户自定义名字的处理
                    MyService service = clazz.getAnnotation(MyService.class);
                    if (!service.value().equals("")) {
                        // 如果用户未自定义名
                        instanceMapping.put(service.value(), clazz.newInstance());
                        continue;
                    }
                    // 再加载其接口类
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        instanceMapping.put(i.getName(), clazz.newInstance());
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private String firstCharToLower(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return Arrays.toString(chars);
    }

    /**
     * 根据包名注册bean
     *
     * @param packageName 包名
     * @author JiaoFanTing
     **/
    private void doRegister(String packageName) {
        // 1. 根据包名获取到 资源路径， 以便递归加载类信息
        URL resource = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        // 2. 递归加载类信息
        File files = new File(Objects.requireNonNull(resource).getFile());
        for (File file : Objects.requireNonNull(files.listFiles())) {
            // 判断是否是文件夹
            if (file.isDirectory()) {
                // 如果是文件夹，递归调用
                doRegister(packageName + "." + file.getName());
            } else {
                // 如果是文件， 将class去掉 ，把全限定文件名 放入classCache
                classCache.add(packageName + "." + file.getName().replaceAll(".class", "").trim());
            }
        }
    }

    /**
     * 获取所有实例对象
     */
    public Map<String, Object> getAll() {
        return instanceMapping;
    }


    /**
     * 获取配置类对象
     */
    public Properties getConfig() {
        return config;
    }
}
