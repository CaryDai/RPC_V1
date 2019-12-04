package hdu.dqj.RPCServer;

import hdu.dqj.RPCServer.Service.HelloService;
import hdu.dqj.RPCServer.Service.HelloServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author dqj
 * @Date 2019/12/3
 * @Version 1.0
 * @Description 服务端提供服务收发的实现。
 */
public class ServerStub {
    private HelloService helloService = new HelloServiceImpl();

    public static void main(String[] args) throws Throwable {
        new ServerStub().run();
    }

    private void run() throws Throwable {
        ServerSocket listener = new ServerSocket(8090);
        System.out.println("Server等待客户端的连接...");
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        Socket socket = null;
        try {
            while (true) {
                socket = listener.accept();
                // 将收到的请求信息反序列化
                input = new ObjectInputStream(socket.getInputStream());
                Object o = input.readObject();
                System.out.println("Server收到：" + o.toString());

                // 调用服务
                String res = helloService.sayHi((String)o);

                // 返回结果
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(res);
                System.out.println("Server返回：" + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) output.close();
            if (input != null) input.close();
            if (socket != null) socket.close();
            listener.close();
        }
    }
}
