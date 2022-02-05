package basic.design.pattern.structural.adapter;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/5
 */
public class AsianLion implements Lion{
    @Override
    public void roar() {
        System.out.println("ao ~ ao ~");
    }
}
