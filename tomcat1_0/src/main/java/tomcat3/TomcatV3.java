package tomcat3;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuegao
 * @version 1.0
 * @date 2022/1/1 18:28
 */
public class TomcatV3 {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("启动服务器....");
        while (!serverSocket.isClosed()) {
            Socket request = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("客户端:" + InetAddress.getLocalHost() + "已连接到服务器");
                        InputStream inputStream = request.getInputStream();
                        System.out.println("收到请求：");
                        // v1
                        // BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        String message = null;
                        while ((message = reader.readLine()) != null) {
                            if (message.length() == 0) {
                                break;
                            }
                            System.out.println(message);
                        }

                        System.out.println("请求结束");

                        OutputStream outputStream = request.getOutputStream();
                        // 像客户端输出响应行/头
                        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                        outputStream.write("Content-Type:text/html;charset=utf8\n".getBytes());
                        outputStream.write("Content-Length:11\n".getBytes());
                        outputStream.write("hello \n".getBytes());
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            request.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }
    }
}