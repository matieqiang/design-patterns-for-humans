package basic.design.pattern.creational.abstractfactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/23
 */
public class AbstractFactoryExample {
    public static void main(String[] args) {
        WoodenDoorFactory woodenDoorFactory = new WoodenDoorFactory();
        Door woodenDoor = woodenDoorFactory.makeDoor();
        woodenDoor.getDescription();
        DoorFittingExpert carpenter = woodenDoorFactory.makeFittingExpert();
        carpenter.getDescription();

        IronDoorFactory ironDoorFactory = new IronDoorFactory();
        Door ironDoor = ironDoorFactory.makeDoor();
        ironDoor.getDescription();
        DoorFittingExpert welder = ironDoorFactory.makeFittingExpert();
        welder.getDescription();
    }
}
