package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class IronDoor implements Door{
    @Override
    public void getDescription() {
        System.out.println("I am a iron door!");
    }
}
