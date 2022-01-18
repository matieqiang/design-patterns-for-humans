package basic.design.pattern.creational.factory;

/**
 * description:
 *
 * 1. 工厂模式是为了解耦：把对象的创建和使用的过程分开。就是Class A 想调用 Class B ，那么A只是调用B的方法，而至于B的实例化，就交给工厂类。
 * 2. 工厂模式可以降低代码重复。我们可以这些创建对象B的代码放到工厂里统一管理。
 * 3. 由于创建过程都由工厂统一管理，所以发生业务逻辑变化，不需要找到所有需要创建B的地方去逐个修正，只需要在工厂里修改即可，降低维护成本。
 * 4. 因为工厂管理了对象的创建逻辑，使用者并不需要知道具体的创建过程，只管使用即可，减少了使用者因为创建逻辑导致的错误。
 *
 * 举个例子：
 * 一个数据库工厂：可以返回一个数据库实例，可以是mysql，oracle等。
 * 这个工厂就可以把数据库连接需要的用户名，地址，密码等封装好，直接返回对应的数据库对象就好。
 * 不需要调用者自己初始化，减少了写错密码等等这些错误。
 * 调用者只负责使用，不需要管怎么去创建、初始化对象。
 *
 * @author mtq
 * date: 2022/1/18
 */
public class FactoryExample {
    public static void main(String[] args) {
        DevelopmentManager developmentManager = new DevelopmentManager();
        developmentManager.takeInterview();

        MarketingManager marketingManager = new MarketingManager();
        marketingManager.takeInterview();
    }
}
