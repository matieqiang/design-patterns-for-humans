package basic.design.pattern.structural.composite;

/**
 * description:
 *
 * 组合模式核心
 * 组合模式将对象组合成 树形结构，以表示 “整体与部分” 的层次结构
 * 组合模式包含3个成员：
 * Leaf： 叶节点，叶节点没有子节点，是整体与部分中的部分
 * Composite： 非叶节点(树枝节点或根节点)，存储子节点，是整体与部分的整体
 * Component： 抽象组件，Leaf 和 Composite 都继承于它，这样才使得用户对整体和部分的使用是一致的（用户并不知道哪个是整体，哪个是部分！）。它定义了所有子类公共的缺省行为
 *
 * @author mtq
 * date: 2022/2/7
 */
public class CompositeSample {
    public static void main(String[] args) {
        Employee john = new Developer(12000f, "John Doe");
        Employee jane = new Designer(11000f, "Jone Doe");

        Organization organization = new Organization();
        organization.addEmployee(john);
        organization.addEmployee(jane);

        System.out.printf("Net salaries: %f",organization.getNetSalaries());
    }
}
