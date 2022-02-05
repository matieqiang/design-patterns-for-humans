package basic.design.pattern.creational.singleton.inclass;

/**
 * description:
 * 优点：避免了线程不安全，延迟加载，效率高。
 * 缺点： 内部类并不是完美解决，可能还存在反射攻击或者反序列化攻击。
 * @author mtq
 * date: 2022/2/5
 */
public class InSingleton {
    private InSingleton() {

    }
    private static class SingletonInHolder {
        private static InSingleton instance = new InSingleton();
    }

    public static InSingleton getInstance() {
        return SingletonInHolder.instance;
    }


}
