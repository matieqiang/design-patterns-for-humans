package basic.design.pattern.structural.adapter;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/5
 */
public class WildDogAdapter implements Lion{
    protected WildDog dog;

    public WildDogAdapter(WildDog dog) {
        this.dog = dog;
    }

    @Override
    public void roar() {
       dog.bark();
    }
}
