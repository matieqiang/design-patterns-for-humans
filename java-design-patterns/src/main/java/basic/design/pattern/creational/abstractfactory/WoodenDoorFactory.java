package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class WoodenDoorFactory implements DoorFactory{
    @Override
    public Door makeDoor() {
        return new WoodenDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Carpenter();
    }
}
