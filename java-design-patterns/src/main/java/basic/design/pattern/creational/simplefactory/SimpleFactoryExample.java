package basic.design.pattern.creational.simplefactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/16
 */
public class SimpleFactoryExample {
    public static void main(String[] args) {
        Door woodenDoor = DoorFactory.makeDoor(1.0F, 2.5F);
        System.out.printf("door1: height is %f,and width is %f \n",woodenDoor.getHeight(),woodenDoor.getWidth());

        Door woodenDoor2 = DoorFactory.makeDoor(2.2F, 3.0F);
        System.out.printf("door1: height is %f,and width is %f",woodenDoor2.getHeight(),woodenDoor2.getWidth());
    }
}
