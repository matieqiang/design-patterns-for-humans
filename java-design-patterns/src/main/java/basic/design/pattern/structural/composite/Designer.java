package basic.design.pattern.structural.composite;

/**
 * description:
 *
 * @author mtq
 * date: 2022/2/7
 */
public class Designer implements Employee{
    protected float salary;
    protected String name;
    protected String[] roles;

    public Designer(float salary, String name) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    @Override
    public String[] getRoles() {
        return new String[0];
    }
}
