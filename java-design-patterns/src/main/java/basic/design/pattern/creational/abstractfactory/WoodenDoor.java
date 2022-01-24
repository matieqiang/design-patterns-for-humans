package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class WoodenDoor implements Door{
    @Override
    public void getDescription() {
        System.out.println("I am a wooden door!");
    }
}
