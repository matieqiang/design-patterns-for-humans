package basic.design.pattern.creational.singleton.lazy;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/4
 */
public class SingletonSample {
    public static void main(String[] args) {
        President p = President.doubleCheckGetInstance();
        President p2 = President.doubleCheckGetInstance();
        System.out.println(p.toString());
        System.out.println(p2.toString());
    }
}
