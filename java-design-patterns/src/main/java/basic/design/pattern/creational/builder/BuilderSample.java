package basic.design.pattern.creational.builder;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/24
 */
public class BuilderSample {
    public static void main(String[] args) {
        Burger burger = new BurgerBuilder(10).addCheese().addLettuce().addPepperoni().addTomato().builder();
    }
}
