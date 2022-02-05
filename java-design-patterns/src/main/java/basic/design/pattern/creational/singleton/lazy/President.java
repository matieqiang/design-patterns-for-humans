package basic.design.pattern.creational.singleton.lazy;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/28
 */
public class President {
    private static President instance;

    private President() {
    }

    /**
     * 线程不安全，不推荐使用
     * @return
     */
    public static President getInstance() {
        // 这个判断是不安全的，如果两个线程同时判断将得到两个实例
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }

    /**
     * 线程安全，但是每次getInstance都需要同步，效率低。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接return就行了。
     * @return
     */
    public static synchronized President synchronizedGetInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }

    public static President doubleCheckGetInstance() {
        if (instance == null) {
            synchronized (President.class) {
                if (instance == null) {
                    instance = new President();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object clone() {
        return this;
    }
}
