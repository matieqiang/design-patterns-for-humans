package basic.design.pattern.creational.singleton.enums;

/**
 * description:
 * 借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 * @author mtq
 * date: 2022/2/5
 */
public enum EnumSingleton {
    INSTANCE;
    public void getInstance() {
        System.out.println("in enum instance");
    }
}
