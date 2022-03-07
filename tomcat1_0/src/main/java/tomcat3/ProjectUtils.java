package tomcat3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/2 16:30
 */
public class ProjectUtils {
    public String projectClassPath = "";

    Map<String, Object> servletMap = new HashMap<>();

    Map<String, Object> servletMappingMap = new HashMap<>();

    Map<String, Object> servletInstanceMap = new HashMap<>();

    public void loadServlet() throws MalformedURLException {
        // URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
                new URL("file:" + projectClassPath + "\\web-info\\classes")
        });
        for (Map.Entry<String, Object> entry : servletMap.entrySet()) {

        }

    }
}