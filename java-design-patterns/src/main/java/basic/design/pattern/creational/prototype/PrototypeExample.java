package basic.design.pattern.creational.prototype;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/27
 */
public class PrototypeExample {
    public static void main(String[] args) {
        var dolly = new Dolly(String.valueOf(System.currentTimeMillis()));
        System.out.println(dolly);
        var dolly2 = dolly.copy();
        System.out.println(dolly2);
    }
}
