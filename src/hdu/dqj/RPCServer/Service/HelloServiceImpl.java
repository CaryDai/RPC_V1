package hdu.dqj.RPCServer.Service;

/**
 * @Author dqj
 * @Date 2019/12/3
 * @Version 1.0
 * @Description Remote端接口的实现。
 */
public class HelloServiceImpl implements HelloService {
    // 方法返回的String类型实现了 java.io.Serializable 接口，因此能被序列化。
    @Override
    public String sayHi(String name) {
        return "Hi," + name;
    }
}
