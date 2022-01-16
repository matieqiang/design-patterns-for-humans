package basic.design.pattern.creational.simplefactory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/16
 */
public class WoodenDoor implements Door{
    private float width;
    private float height;

    protected WoodenDoor(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
