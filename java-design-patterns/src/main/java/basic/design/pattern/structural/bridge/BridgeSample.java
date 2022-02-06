package basic.design.pattern.structural.bridge;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/6
 */
public class BridgeSample {
    public static void main(String[] args) {
        DarkTheme darkTheme = new DarkTheme();
        About aboutPage = new About(darkTheme);
        Careers careersPage = new Careers(darkTheme);

        System.out.println(aboutPage.getContent());
        System.out.println(careersPage.getContent());
    }
}
