package basic.design.pattern.structural.bridge;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/6
 */
public class About implements WebPage{
    protected Theme theme;

    public About(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "about page in " + theme.getColor();
    }
}
