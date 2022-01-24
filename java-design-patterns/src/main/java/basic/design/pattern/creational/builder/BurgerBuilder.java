package basic.design.pattern.creational.builder;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/24
 */
public class BurgerBuilder {
    public int size;
    public boolean cheese;
    public boolean pepperoni;
    public boolean lettuce;
    public boolean tomato;

    public BurgerBuilder(int size) {
        this.size = size;
    }

    public BurgerBuilder addPepperoni() {
        this.pepperoni = true;
        return this;
    }

    public BurgerBuilder addLettuce() {
        this.lettuce = true;
        return this;
    }

    public BurgerBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public BurgerBuilder addTomato() {
        this.tomato = true;
        return this;
    }

    public Burger builder(){
        return new Burger(this);
    }
}
