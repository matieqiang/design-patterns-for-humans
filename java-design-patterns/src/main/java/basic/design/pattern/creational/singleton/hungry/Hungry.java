package basic.design.pattern.creational.singleton.hungry;

/**
 * description:
 *
 * 饥饿模式在类加载时就已经创建了实例，不用再通过synchronized和volatile 处理线程安全问题
 * @author mtq
 * date: 2022/2/5
 */
public class Hungry {
    private static Hungry instance = new Hungry();

    public static Hungry getInstance() {
        return instance;
    }
}
