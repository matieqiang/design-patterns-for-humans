package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class Carpenter implements DoorFittingExpert{
    @Override
    public void getDescription() {
        System.out.println("I can only fit wooden doors !");
    }
}
