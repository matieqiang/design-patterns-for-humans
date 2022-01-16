package basic.design.pattern.creational.simplefactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/16
 */
public class DoorFactory {
    public static Door makeDoor(float width, float height) {
        return new WoodenDoor(width,height);
    }
}
