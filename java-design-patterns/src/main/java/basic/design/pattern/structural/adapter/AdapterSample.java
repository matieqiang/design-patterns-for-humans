package basic.design.pattern.structural.adapter;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/5
 */
public class AdapterSample {
    public static void main(String[] args) {
        Hunter hunter = new Hunter();

        AfricanLion africanLion = new AfricanLion();
        AsianLion asianLion = new AsianLion();
        hunter.hunt(africanLion);
        hunter.hunt(asianLion);

        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);
        hunter.hunt(wildDogAdapter);

    }
}
