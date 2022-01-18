package basic.design.pattern.creational.factory;

/**
 * description:
 *
 * @author mtq
 * date: 2022/1/18
 */
public abstract class HiringManager {

    // factory method
    abstract protected Interviewer makeInterviewer();
    public void takeInterview() {
        Interviewer interviewer = this.makeInterviewer();
        interviewer.askQuestions();
    }
}
