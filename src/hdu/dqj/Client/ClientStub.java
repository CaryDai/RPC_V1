package hdu.dqj.Client;

import hdu.dqj.RPCServer.Service.HelloService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author dqj
 * @Date 2019/12/3
 * @Version 1.0
 * @Description 这个类把RPC逻辑封装进去，客户端调用时感知不到远程调用的麻烦。
 */
public class ClientStub implements HelloService {
    private static final int PORT = 8090;   // 服务器端端口号
    private static final String address = "127.0.0.1";  // 服务器端IP地址

    public String sayHi(String name) throws Throwable {
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        Socket socket = null;
        try {
            socket = new Socket(address, PORT);
            System.out.println("Client传递信息中...");
            // 将请求信息序列化
            output = new ObjectOutputStream(socket.getOutputStream());
            // 将请求发给服务提供方
            output.writeObject(name);

            // 将响应信息反序列化
            input = new ObjectInputStream(socket.getInputStream());
            Object response = input.readObject();
            System.out.println("Client收到消息！");
            return (String) response;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) output.close();
            if (input != null)  input.close();
            if (socket != null) socket.close();
        }
        return null;
    }
}
