package basic.design.pattern.creational.prototype;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/27
 */
public abstract class Sheep implements Prototype{

    public Sheep() {
    }

    public Sheep(Sheep source) {
    }

    @Override
    public abstract Sheep copy();
}
