package basic.design.pattern.structural.bridge;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/6
 */
public class Careers implements WebPage{
    protected Theme theme;

    public Careers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Careers page in " + theme.getColor();
    }
}
