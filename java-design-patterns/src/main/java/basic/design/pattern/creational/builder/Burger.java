package basic.design.pattern.creational.builder;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/24
 */
public class Burger {
    protected int size;
    protected boolean cheese;
    protected boolean pepperoni;
    protected boolean lettuce;
    protected boolean tomato;

    public Burger(BurgerBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.lettuce = builder.lettuce;
        this.tomato = builder.tomato;
    }
}
