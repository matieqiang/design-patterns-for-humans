package basic.design.pattern.creational.factory;

/**
 * description:
 *
 * factory
 *
 * @author mtq
 * date: 2022/1/18
 */
public class MarketingManager extends HiringManager{
    @Override
    protected Interviewer makeInterviewer() {
        return new CommunityExecutive();
    }
}
