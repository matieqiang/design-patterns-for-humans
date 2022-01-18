package basic.design.pattern.creational.factory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/18
 */
public class Developer implements Interviewer{
    @Override
    public void askQuestions() {
        System.out.println("Asking about design patterns!");
    }
}
