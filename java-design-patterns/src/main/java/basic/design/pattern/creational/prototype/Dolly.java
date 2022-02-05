package basic.design.pattern.creational.prototype;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/27
 */
public class Dolly extends Sheep{
    private final String dob;

    public Dolly(String dob) {
        this.dob = dob;
    }

    public Dolly(Dolly dolly) {
        super(dolly);
        this.dob = dolly.dob;
    }

    @Override
    public Dolly copy() {
        return new Dolly(this);
    }
}
