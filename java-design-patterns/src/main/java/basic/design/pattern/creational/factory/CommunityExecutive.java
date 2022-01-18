package basic.design.pattern.creational.factory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/18
 */
public class CommunityExecutive implements Interviewer{
    @Override
    public void askQuestions() {
        System.out.println("Asking about community building!");
    }
}
