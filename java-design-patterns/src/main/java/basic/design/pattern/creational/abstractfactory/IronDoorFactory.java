package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class IronDoorFactory implements DoorFactory{
    @Override
    public Door makeDoor() {
        return new IronDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Welder();
    }
}
