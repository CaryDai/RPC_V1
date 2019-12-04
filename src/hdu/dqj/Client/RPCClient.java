package hdu.dqj.Client;

import hdu.dqj.RPCServer.Service.HelloService;

/**
 * @Author dqj
 * @Date 2019/12/3
 * @Version 1.0
 * @Description RPC的客户端，用于调用远程的接口实现类。
 */
public class RPCClient {
    public static void main(String[] args) throws Throwable {
        HelloService helloService = new ClientStub();
        String str = helloService.sayHi("XD");
        System.out.println(str);
    }
}
