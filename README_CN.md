![Design Patterns For Humans](https://cloud.githubusercontent.com/assets/11269635/23065273/1b7e5938-f515-11e6-8dd3-d0d58de6bb9a.png)

***

<p align="center">
🎉 对设计模式的超级简化的解释! 🎉
</p>
<p align="center">
一个很容易让任何人动摇的话题。在这里，我试着用最简单的方式来解释它们，让它们深深印在你的脑海里(也许还有我的脑海)。
</p>

***

<sub>Check out my [blog](http://kamranahmed.info) and say "hi" on [Twitter](https://twitter.com/kamranahmedse).</sub>


新增内容
=====
除了中文翻译还增加了java代码实例

Introduction
=================

设计模式是对重复出现的问题的解决方案；关于如何解决某些问题的指导方针。它们不是可以插入应用程序并等待魔法发生的类、包或库。相反，这些是关于如何在某些情况下解决某些问题的指导方针。

> 设计模式是对重复出现的问题的解决方案；关于如何解决某些问题的指南

Wikipedia describes them as

> 在软件工程中，软件设计模式是针对软件设计中给定上下文中常见问题的通用可重用解决方案。它不是可以直接转换为源代码或机器代码的完成设计。它是关于如何解决问题的描述或模板，可以在许多不同的情况下使用。

⚠️ Be Careful
-----------------
- Design patterns are not a silver bullet to all your problems.
- Do not try to force them; bad things are supposed to happen, if done so. 
- Keep in mind that design patterns are solutions **to** problems, not solutions **finding** problems; so don't overthink.
- If used in a correct place in a correct manner, they can prove to be a savior; or else they can result in a horrible mess of a code.

> 另请注意，下面的代码示例是在 PHP-7 中，但这不应该阻止您，因为无论如何概念都是相同的。

Types of Design Patterns
-----------------

* [Creational](#creational-design-patterns)
* [Structural](#structural-design-patterns)
* [Behavioral](#behavioral-design-patterns)

Creational Design Patterns
==========================

In plain words
> Creational patterns are focused towards how to instantiate an object or group of related objects.

Wikipedia says
> 在软件工程中，创建设计模式是处理对象创建机制的设计模式，试图以适合情况的方式创建对象。对象创建的基本形式可能会导致设计问题或增加设计的复杂性。创建型设计模式通过以某种方式控制此对象的创建来解决此问题。

 * [Simple Factory](#-simple-factory)
 * [Factory Method](#-factory-method)
 * [Abstract Factory](#-abstract-factory)
 * [Builder](#-builder)
 * [Prototype](#-prototype)
 * [Singleton](#-singleton)

🏠 Simple Factory
--------------
Real world example
> 考虑一下，您正在建造房屋，并且需要门。你可以穿上你的木匠衣服，带上一些木头、胶水、钉子和建造门所需的所有工具，然后开始在你的房子里建造它，或者你可以简单地打电话给工厂，把建造好的门送到你手上不需要学习任何关于门制作的知识或处理制作它所带来的混乱。

In plain words
> Simple factory simply generates an instance for client without exposing any instantiation logic to the client

Wikipedia says
> 在面向对象编程 (OOP) 中，工厂是一个用于创建其他对象的对象——正式地说，工厂是一个函数或方法，它从某个方法调用返回不同原型或类的对象，承担new的工作。

**Programmatic Example**

First all we have a door interface and the implementation
```java
public interface Door {
    float getWidth();
    float getHeight();
}

public class WoodenDoor implements Door{
    private float width;
    private float height;

    protected WoodenDoor(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
```
Then we have our door factory that makes the door and returns it
```java
public class DoorFactory {
    public static Door makeDoor(float width, float height) {
        return new WoodenDoor(width,height);
    }
}
```
And then it can be used as
```java
public class SimpleFactoryExample {
    public static void main(String[] args) {
        Door woodenDoor = DoorFactory.makeDoor(1.0F, 2.5F);
        System.out.printf("door1: height is %f,and width is %f \n",woodenDoor.getHeight(),woodenDoor.getWidth());

        Door woodenDoor2 = DoorFactory.makeDoor(2.2F, 3.0F);
        System.out.printf("door1: height is %f,and width is %f",woodenDoor2.getHeight(),woodenDoor2.getWidth());
    }
}
```

**When to Use?**

当创建一个对象不仅仅是一些分配并且涉及一些逻辑时，将它放在一个专用的工厂中而不是在任何地方重复相同的代码是有意义的。

**简单工厂结构**
![](./java-design-patterns/images/simplefactory.png)


🏭 Factory Method
--------------

Real world example
> 考虑一下招聘经理的工作场景。他不可能一个人面试每个职位。所以他应该根据职位空缺，将面试步骤委托给不同的人。 

In plain words
> 它提供了一种将实例化逻辑委托给子类的方法。

Wikipedia says
> 在基于类的编程中，工厂方法模式是一种创建模式，它使用工厂方法来处理创建对象的问题，而不必指定将要创建的对象的确切类。
> 这是通过调用工厂方法（在接口中指定并由子类实现，或在基类中实现并可选地由派生类覆盖）而不是通过调用构造函数来创建对象来完成的。

 **Programmatic Example**

以招聘经理为例。首先，有一个面试官接口和一些实现类。

```java
public interface Interviewer {
    void askQuestions();
}

public class Developer implements Interviewer{
    @Override
    public void askQuestions() {
        System.out.println("Asking about design patterns!");
    }
}

public class CommunityExecutive implements Interviewer{
    @Override
    public void askQuestions() {
        System.out.println("Asking about community building!");
    }
}
```

Now let us create our `HiringManager`

```java
public abstract class HiringManager {

    // factory method
    abstract protected Interviewer makeInterviewer();
    public void takeInterview() {
        Interviewer interviewer = this.makeInterviewer();
        interviewer.askQuestions();
    }
}

```
Now any child can extend it and provide the required interviewer
```java
public class DevelopmentManager extends HiringManager{
    @Override
    protected Interviewer makeInterviewer() {
        return new Developer();
    }
}

public class MarketingManager extends HiringManager{
    @Override
    protected Interviewer makeInterviewer() {
        return new CommunityExecutive();
    }
}
```

and then it can be used as
```java
public class FactoryExample {
    public static void main(String[] args) {
        DevelopmentManager developmentManager = new DevelopmentManager();
        developmentManager.takeInterview();

        MarketingManager marketingManager = new MarketingManager();
        marketingManager.takeInterview();
    }
}
```

**When to use?**

当类中有一些通用处理但所需的子类在运行时动态决定时很有用。或者换句话说，当客户不知道它可能需要什么确切的子类时。

**进一步理解factory的应用场景**
1. 用于需要解耦的场景
2. 用于减少重复代码
3. 创建过程统一有工厂管理，便于维护。
4. 由于使用者不参与创建过程，可能会减少由于创建逻辑造成的错误。

🔨 Abstract Factory
----------------

Real world example
> 从简单工厂扩展我们的门示例。根据您的需要，您可能会从木门店购买木门，从铁店购买铁门或从相关商店购买 PVC 门。
> 另外，您可能需要一个具有不同专业知识的人来安装门，例如木门的木匠，铁门的焊工等。正如您所看到的现在门之间存在依赖关系，木门需要木匠，铁门需要焊工等

In plain words
> 工厂的工厂，将单个但相关或依赖的工厂组合在一起，而不指定它们的具体类的工厂。

Wikipedia says
> 抽象工厂模式提供了一种方法来封装一组具有共同主题的独立工厂，而无需指定它们的具体类

**Programmatic Example**
转换上面的门的例子，首先，我们有Door接口，和一些接口实现。

```java
public interface Door {
    void getDescription();
}

public class WoodenDoor implements Door{
    @Override
    public void getDescription() {
        System.out.println("I am a wooden door!");
    }
}

public class IronDoor implements Door{
    @Override
    public void getDescription() {
        System.out.println("I am a iron door!");
    }
}

```
Then we have some fitting experts for each door type

```java
public interface DoorFittingExpert {
    void getDescription();
}

public class Welder implements DoorFittingExpert{
    @Override
    public void getDescription() {
        System.out.println("I can only fit iron doors !");
    }
}

public class Carpenter implements DoorFittingExpert{
    @Override
    public void getDescription() {
        System.out.println("I can only fit wooden doors !");
    }
}

```
现在我们准备抽象工厂，可以让我们创建一组相关对象。
i.e. 
木门工厂将创建木门和木匠对象，铁门工厂可以创建铁门和焊接工对象。
```java
public interface DoorFactory {
    Door makeDoor();
    DoorFittingExpert makeFittingExpert();
}

// Wooden factory to return carpenter and wooden door
public class WoodenDoorFactory implements DoorFactory{
    @Override
    public Door makeDoor() {
        return new WoodenDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Carpenter();
    }
}

// Iron door factory to get iron door and the relevant fitting expert
public class IronDoorFactory implements DoorFactory{
    @Override
    public Door makeDoor() {
        return new IronDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Welder();
    }
}
```
And then it can be used as
```java
public class AbstractFactoryExample {
    public static void main(String[] args) {
        WoodenDoorFactory woodenDoorFactory = new WoodenDoorFactory();
        Door woodenDoor = woodenDoorFactory.makeDoor();
        woodenDoor.getDescription();
        DoorFittingExpert carpenter = woodenDoorFactory.makeFittingExpert();
        carpenter.getDescription();

        IronDoorFactory ironDoorFactory = new IronDoorFactory();
        Door ironDoor = ironDoorFactory.makeDoor();
        ironDoor.getDescription();
        DoorFittingExpert welder = ironDoorFactory.makeFittingExpert();
        welder.getDescription();
    }
}
```
如您所见，木门厂封装了carpenter和wooden door，铁门厂也封装了iron door和welder。因此，它帮助我们确保对于每个创建的门，我们都不会弄错装配专家。

**When to use?**
当有一个创建逻辑没那么简单的相关依赖时。

👷 Builder
--------------------------------------------
Real world example
> 在某些情况下，创建逻辑可能涉及更多步骤。例如，您想要一个定制的赛百味交易，您在制作汉堡时有多种选择，例如您想要什么面包？你想要什么类型的酱汁？你想要什么奶酪？等等。在这种情况下，建造者模式可以提供帮助。

In plain words
> 允许您创建对象的不同风格，同时避免构造函数污染。当一个对象可能有多种风格时很有用。或者当创建对象涉及很多步骤时。

Wikipedia says
> 创建者模式是一个对象创建的软件设计模式，意图找到一种解决伸缩构造器反模式的方法。

话虽如此，让我补充一点关于什么是伸缩构造函数反模式。在某一时刻，我们都见过如下构造函数：

```java
public Burger(int size, boolean cheese, boolean pepperoni, boolean lettuce, boolean tomato) {
    this.size = size;
    this.cheese = cheese;
    this.pepperoni = pepperoni;
    this.lettuce = lettuce;
    this.tomato = tomato;
}
```
如你看到的; 构造函数参数的数量很快就会失控，并且可能难以理解参数的排列。另外，如果您将来想添加更多选项，此参数列表可能会继续增长。这称为伸缩构造函数反模式。
**Programmatic Example**

明智的选择是使用构建器模式。首先我们有我们想做的汉堡

```java
public class Burger {
    protected int size;
    protected boolean cheese;
    protected boolean pepperoni;
    protected boolean lettuce;
    protected boolean tomato;

    public Burger(BurgerBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.lettuce = builder.lettuce;
        this.tomato = builder.tomato;
    }
}
```

And then we have the builder

```java
public class BurgerBuilder {
    public int size;
    public boolean cheese;
    public boolean pepperoni;
    public boolean lettuce;
    public boolean tomato;

    public BurgerBuilder(int size) {
        this.size = size;
    }

    public BurgerBuilder addPepperoni() {
        this.pepperoni = true;
        return this;
    }

    public BurgerBuilder addLettuce() {
        this.lettuce = true;
        return this;
    }

    public BurgerBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public BurgerBuilder addTomato() {
        this.tomato = true;
        return this;
    }

    public Burger builder(){
        return new Burger(this);
    }
}
```
And then it can be used as:

```java
Burger burger = new BurgerBuilder(10).addCheese().addLettuce().addPepperoni().addTomato().builder();
```

**When to use?**

当一个对象可能有多种风格并避免构造函数伸缩时。与工厂模式的主要区别在于：当创建是一个步骤过程时，将使用工厂模式，而当创建是多步骤过程时，将使用构建器模式。

🐑 Prototype
------------
Real world example
> 记得多莉吗，那只克隆羊，详细情况咱们不用说了，但这里的关键点是，这一切都与克隆有关

In plain words
> 基于一个已经存在的对象通过clone方法创建一个新对象。

Wikipedia says

> 原型模式是软件开发中的一种创造型设计模式。 当要创建的对象的类型由一个原型实例决定时，它被用来克隆以产生新的对象。

简而言之，它允许你创建一个现有对象的副本，并根据你的需要对其进行修改，而不是费力地从头创建一个对象并对其进行设置。

**Programmatic Example**

在Java中，原型模式建议用这样的方法实现。首先，创建一个带有克隆对象方法的接口。在这个例子中，Prototype 接口通过它的 copy 方法实现了这一点。
```java
public interface Prototype {
    Object copy();
}

public abstract class Sheep implements Prototype{

    public Sheep() {
    }

    public Sheep(Sheep source) {
    }

    @Override
    public abstract Sheep copy();
}

public class Dolly extends Sheep{
    private final String dob;

    public Dolly(String dob) {
        this.dob = dob;
    }

    public Dolly(Dolly dolly) {
        super(dolly);
        this.dob = dolly.dob;
    }

    @Override
    public Dolly copy() {
        return new Dolly(this);
    }
}

```

为了充分利用原型模式，我们创建了`SheepFactory'和`SheepFactoryImpl'两个类，以便从原型中产生不同种类的生物。

Then it can be cloned like below
```java
public class PrototypeExample {
    public static void main(String[] args) {
        var dolly = new Dolly(String.valueOf(System.currentTimeMillis()));
        System.out.println(dolly);
        var dolly2 = dolly.copy();
        System.out.println(dolly2);
    }
}
```

Also you could use the magic method `__clone` to modify the cloning behavior.

**When to use?**

当需要一个与现有对象相似的对象时，或者与克隆相比，创建对象的成本很高时。

💍 Singleton
------------
Real world example
> 一个国家在一个时期点只有一个总统，无论什么时候呼叫，应答的应该都是统一位总统。这个总统是个唯一的一个。

In plain words
> 在任何时候都要确保一个特定的类只有一个对象。

Wikipedia says
> 在软件工程里，单例模式是一个约束一个类只有一个对象的软件设计模式，当只需要一个对象来协调整个系统的操作时，这是非常有用的。

单例模式实际上被认为是一种反模式，应该避免过度使用它。 它不一定是坏的，可能有一些有效的用例，但应该谨慎使用，因为它在应用程序中引入了一个全局状态，在一个地方更改它可能会影响到其他地方，并且它可能变得非常难以调试。 它们的另一个缺点是，它使代码紧密耦合，而且模仿单例可能很困难。

**Programmatic Example**

创建一个单例，将构造器设置为私有，禁用克隆，禁用扩展并创建一个静态变量用来容纳实例。

```java
public class President {
    private static President instance;
    private President() {
    }
    public static President getInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }
    public static synchronized President synchronizedGetInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }
    public static President doubleCheckGetInstance() {
        if (instance == null) {
            synchronized (President.class) {
                if (instance == null) {
                    instance = new President();
                }
            }
        }
        return instance;
    }
    @Override
    protected Object clone() {
        return this;
    }
}
```
Then in order to use
```java
public class SingletonSample {
    public static void main(String[] args) {
        President p = President.doubleCheckGetInstance();
        President p2 = President.doubleCheckGetInstance();
        System.out.println(p.toString());
        System.out.println(p2.toString());
    }
}

```

Structural Design Patterns
==========================
In plain words
> 结构模式主要涉及对象组成，或者换句话说，实体如何相互使用。或者另一种解释是，它们有助于回答“如何构建软件组件？”

Wikipedia says
> 在软件工程中，结构设计模式是通过识别实现实体之间关系的简单方法来简化设计的设计模式。

 * [Adapter](#-adapter)
 * [Bridge](#-bridge)
 * [Composite](#-composite)
 * [Decorator](#-decorator)
 * [Facade](#-facade)
 * [Flyweight](#-flyweight)
 * [Proxy](#-proxy)

🔌 Adapter
-------
Real world example
> 考虑一下这个场景，您的存储卡中有一些照片，需要将它们传输到计算机上。为了传输它们，您需要某种与您的计算机端口兼容的适配器，以便您可以将存储卡连接到您的计算机。在这种情况下，读卡器是适配器。
> 另一个例子是著名的电源适配器; 三脚插头不能连接到双管插座，需要使用电源适配器，使其与双叉插座兼容。
> 还有一个例子是翻译人员将一个人所说的话翻译成另一个人

In plain words
> 适配器模式允许您在适配器中包装其他不兼容的对象，以使其与另一个类兼容。

Wikipedia says
> 在软件工程中，适配器模式是一种软件设计模式，它允许将现有类的接口用作另一个接口。它通常用于使现有类与其他类一起工作而无需修改其源代码。

**Programmatic Example**

考虑一下猎人猎杀狮子的场景。
首先有一个Lion接口，所有狮子必须实现这个接口。

```java
public interface Lion {
    void roar();
}

public class AfricanLion implements Lion{
    @Override
    public void roar() {
        System.out.println("ao ~");
    }
}

public class AsianLion implements Lion{
    @Override
    public void roar() {
        System.out.println("ao ~ ao ~");
    }
}

```
猎人能狗追踪任何实现Lion接口的狮子

```java
public class Hunter {
    public void hunt(Lion lion) {
        lion.roar();
    }
}
```
现在我们添加一个野狗（WildDog），让猎人也可以追踪它。
但是，我们不能直接追踪它，因为野狗有一个不同的接口，为了兼容我们的猎人，我们将创建一个兼容的适配器。

```java
// This needs to be added to the game
public class WildDog {
    public void bark() {
        System.out.println("wang wang wang ~");
    }
}

// Adapter around wild dog to make it compatible with our game
public class WildDogAdapter implements Lion{
    protected WildDog dog;

    public WildDogAdapter(WildDog dog) {
        this.dog = dog;
    }

    @Override
    public void roar() {
        dog.bark();
    }
}
```
现在野狗可以通过WildDogAdapter也可以在这个游戏中被使用。

```java
public class AdapterSample {
    public static void main(String[] args) {
        Hunter hunter = new Hunter();

        AfricanLion africanLion = new AfricanLion();
        AsianLion asianLion = new AsianLion();
        hunter.hunt(africanLion);
        hunter.hunt(asianLion);

        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);
        hunter.hunt(wildDogAdapter);

    }
}
```

🚡 Bridge
------
Real world example
> Consider you have a website with different pages and you are supposed to allow the user to change the theme. What would you do? Create multiple copies of each of the pages for each of the themes or would you just create separate theme and load them based on the user's preferences? Bridge pattern allows you to do the second i.e.

![With and without the bridge pattern](https://cloud.githubusercontent.com/assets/11269635/23065293/33b7aea0-f515-11e6-983f-98823c9845ee.png)

In Plain Words
> Bridge pattern is about preferring composition over inheritance. Implementation details are pushed from a hierarchy to another object with a separate hierarchy.

Wikipedia says
> The bridge pattern is a design pattern used in software engineering that is meant to "decouple an abstraction from its implementation so that the two can vary independently"

**Programmatic Example**

Translating our WebPage example from above. Here we have the `WebPage` hierarchy

```php
interface WebPage
{
    public function __construct(Theme $theme);
    public function getContent();
}

class About implements WebPage
{
    protected $theme;

    public function __construct(Theme $theme)
    {
        $this->theme = $theme;
    }

    public function getContent()
    {
        return "About page in " . $this->theme->getColor();
    }
}

class Careers implements WebPage
{
    protected $theme;

    public function __construct(Theme $theme)
    {
        $this->theme = $theme;
    }

    public function getContent()
    {
        return "Careers page in " . $this->theme->getColor();
    }
}
```
And the separate theme hierarchy
```php

interface Theme
{
    public function getColor();
}

class DarkTheme implements Theme
{
    public function getColor()
    {
        return 'Dark Black';
    }
}
class LightTheme implements Theme
{
    public function getColor()
    {
        return 'Off white';
    }
}
class AquaTheme implements Theme
{
    public function getColor()
    {
        return 'Light blue';
    }
}
```
And both the hierarchies
```php
$darkTheme = new DarkTheme();

$about = new About($darkTheme);
$careers = new Careers($darkTheme);

echo $about->getContent(); // "About page in Dark Black";
echo $careers->getContent(); // "Careers page in Dark Black";
```

🌿 Composite
-----------------

Real world example
> Every organization is composed of employees. Each of the employees has the same features i.e. has a salary, has some responsibilities, may or may not report to someone, may or may not have some subordinates etc.

In plain words
> Composite pattern lets clients treat the individual objects in a uniform manner.

Wikipedia says
> In software engineering, the composite pattern is a partitioning design pattern. The composite pattern describes that a group of objects is to be treated in the same way as a single instance of an object. The intent of a composite is to "compose" objects into tree structures to represent part-whole hierarchies. Implementing the composite pattern lets clients treat individual objects and compositions uniformly.

**Programmatic Example**

Taking our employees example from above. Here we have different employee types

```php
interface Employee
{
    public function __construct(string $name, float $salary);
    public function getName(): string;
    public function setSalary(float $salary);
    public function getSalary(): float;
    public function getRoles(): array;
}

class Developer implements Employee
{
    protected $salary;
    protected $name;
    protected $roles;
    
    public function __construct(string $name, float $salary)
    {
        $this->name = $name;
        $this->salary = $salary;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function setSalary(float $salary)
    {
        $this->salary = $salary;
    }

    public function getSalary(): float
    {
        return $this->salary;
    }

    public function getRoles(): array
    {
        return $this->roles;
    }
}

class Designer implements Employee
{
    protected $salary;
    protected $name;
    protected $roles;

    public function __construct(string $name, float $salary)
    {
        $this->name = $name;
        $this->salary = $salary;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function setSalary(float $salary)
    {
        $this->salary = $salary;
    }

    public function getSalary(): float
    {
        return $this->salary;
    }

    public function getRoles(): array
    {
        return $this->roles;
    }
}
```

Then we have an organization which consists of several different types of employees

```php
class Organization
{
    protected $employees;

    public function addEmployee(Employee $employee)
    {
        $this->employees[] = $employee;
    }

    public function getNetSalaries(): float
    {
        $netSalary = 0;

        foreach ($this->employees as $employee) {
            $netSalary += $employee->getSalary();
        }

        return $netSalary;
    }
}
```

And then it can be used as

```php
// Prepare the employees
$john = new Developer('John Doe', 12000);
$jane = new Designer('Jane Doe', 15000);

// Add them to organization
$organization = new Organization();
$organization->addEmployee($john);
$organization->addEmployee($jane);

echo "Net salaries: " . $organization->getNetSalaries(); // Net Salaries: 27000
```

☕ Decorator
-------------

Real world example

> Imagine you run a car service shop offering multiple services. Now how do you calculate the bill to be charged? You pick one service and dynamically keep adding to it the prices for the provided services till you get the final cost. Here each type of service is a decorator.

In plain words
> Decorator pattern lets you dynamically change the behavior of an object at run time by wrapping them in an object of a decorator class.

Wikipedia says
> In object-oriented programming, the decorator pattern is a design pattern that allows behavior to be added to an individual object, either statically or dynamically, without affecting the behavior of other objects from the same class. The decorator pattern is often useful for adhering to the Single Responsibility Principle, as it allows functionality to be divided between classes with unique areas of concern.

**Programmatic Example**

Lets take coffee for example. First of all we have a simple coffee implementing the coffee interface

```php
interface Coffee
{
    public function getCost();
    public function getDescription();
}

class SimpleCoffee implements Coffee
{
    public function getCost()
    {
        return 10;
    }

    public function getDescription()
    {
        return 'Simple coffee';
    }
}
```
We want to make the code extensible to allow options to modify it if required. Lets make some add-ons (decorators)
```php
class MilkCoffee implements Coffee
{
    protected $coffee;

    public function __construct(Coffee $coffee)
    {
        $this->coffee = $coffee;
    }

    public function getCost()
    {
        return $this->coffee->getCost() + 2;
    }

    public function getDescription()
    {
        return $this->coffee->getDescription() . ', milk';
    }
}

class WhipCoffee implements Coffee
{
    protected $coffee;

    public function __construct(Coffee $coffee)
    {
        $this->coffee = $coffee;
    }

    public function getCost()
    {
        return $this->coffee->getCost() + 5;
    }

    public function getDescription()
    {
        return $this->coffee->getDescription() . ', whip';
    }
}

class VanillaCoffee implements Coffee
{
    protected $coffee;

    public function __construct(Coffee $coffee)
    {
        $this->coffee = $coffee;
    }

    public function getCost()
    {
        return $this->coffee->getCost() + 3;
    }

    public function getDescription()
    {
        return $this->coffee->getDescription() . ', vanilla';
    }
}
```

Lets make a coffee now

```php
$someCoffee = new SimpleCoffee();
echo $someCoffee->getCost(); // 10
echo $someCoffee->getDescription(); // Simple Coffee

$someCoffee = new MilkCoffee($someCoffee);
echo $someCoffee->getCost(); // 12
echo $someCoffee->getDescription(); // Simple Coffee, milk

$someCoffee = new WhipCoffee($someCoffee);
echo $someCoffee->getCost(); // 17
echo $someCoffee->getDescription(); // Simple Coffee, milk, whip

$someCoffee = new VanillaCoffee($someCoffee);
echo $someCoffee->getCost(); // 20
echo $someCoffee->getDescription(); // Simple Coffee, milk, whip, vanilla
```

📦 Facade
----------------

Real world example
> How do you turn on the computer? "Hit the power button" you say! That is what you believe because you are using a simple interface that computer provides on the outside, internally it has to do a lot of stuff to make it happen. This simple interface to the complex subsystem is a facade.

In plain words
> Facade pattern provides a simplified interface to a complex subsystem.

Wikipedia says
> A facade is an object that provides a simplified interface to a larger body of code, such as a class library.

**Programmatic Example**

Taking our computer example from above. Here we have the computer class

```php
class Computer
{
    public function getElectricShock()
    {
        echo "Ouch!";
    }

    public function makeSound()
    {
        echo "Beep beep!";
    }

    public function showLoadingScreen()
    {
        echo "Loading..";
    }

    public function bam()
    {
        echo "Ready to be used!";
    }

    public function closeEverything()
    {
        echo "Bup bup bup buzzzz!";
    }

    public function sooth()
    {
        echo "Zzzzz";
    }

    public function pullCurrent()
    {
        echo "Haaah!";
    }
}
```
Here we have the facade
```php
class ComputerFacade
{
    protected $computer;

    public function __construct(Computer $computer)
    {
        $this->computer = $computer;
    }

    public function turnOn()
    {
        $this->computer->getElectricShock();
        $this->computer->makeSound();
        $this->computer->showLoadingScreen();
        $this->computer->bam();
    }

    public function turnOff()
    {
        $this->computer->closeEverything();
        $this->computer->pullCurrent();
        $this->computer->sooth();
    }
}
```
Now to use the facade
```php
$computer = new ComputerFacade(new Computer());
$computer->turnOn(); // Ouch! Beep beep! Loading.. Ready to be used!
$computer->turnOff(); // Bup bup buzzz! Haah! Zzzzz
```

🍃 Flyweight
---------

Real world example
> Did you ever have fresh tea from some stall? They often make more than one cup that you demanded and save the rest for any other customer so to save the resources e.g. gas etc. Flyweight pattern is all about that i.e. sharing.

In plain words
> It is used to minimize memory usage or computational expenses by sharing as much as possible with similar objects.

Wikipedia says
> In computer programming, flyweight is a software design pattern. A flyweight is an object that minimizes memory use by sharing as much data as possible with other similar objects; it is a way to use objects in large numbers when a simple repeated representation would use an unacceptable amount of memory.

**Programmatic example**

Translating our tea example from above. First of all we have tea types and tea maker

```php
// Anything that will be cached is flyweight.
// Types of tea here will be flyweights.
class KarakTea
{
}

// Acts as a factory and saves the tea
class TeaMaker
{
    protected $availableTea = [];

    public function make($preference)
    {
        if (empty($this->availableTea[$preference])) {
            $this->availableTea[$preference] = new KarakTea();
        }

        return $this->availableTea[$preference];
    }
}
```

Then we have the `TeaShop` which takes orders and serves them

```php
class TeaShop
{
    protected $orders;
    protected $teaMaker;

    public function __construct(TeaMaker $teaMaker)
    {
        $this->teaMaker = $teaMaker;
    }

    public function takeOrder(string $teaType, int $table)
    {
        $this->orders[$table] = $this->teaMaker->make($teaType);
    }

    public function serve()
    {
        foreach ($this->orders as $table => $tea) {
            echo "Serving tea to table# " . $table;
        }
    }
}
```
And it can be used as below

```php
$teaMaker = new TeaMaker();
$shop = new TeaShop($teaMaker);

$shop->takeOrder('less sugar', 1);
$shop->takeOrder('more milk', 2);
$shop->takeOrder('without sugar', 5);

$shop->serve();
// Serving tea to table# 1
// Serving tea to table# 2
// Serving tea to table# 5
```

🎱 Proxy
-------------------
Real world example
> Have you ever used an access card to go through a door? There are multiple options to open that door i.e. it can be opened either using access card or by pressing a button that bypasses the security. The door's main functionality is to open but there is a proxy added on top of it to add some functionality. Let me better explain it using the code example below.

In plain words
> Using the proxy pattern, a class represents the functionality of another class.

Wikipedia says
> A proxy, in its most general form, is a class functioning as an interface to something else. A proxy is a wrapper or agent object that is being called by the client to access the real serving object behind the scenes. Use of the proxy can simply be forwarding to the real object, or can provide additional logic. In the proxy extra functionality can be provided, for example caching when operations on the real object are resource intensive, or checking preconditions before operations on the real object are invoked.

**Programmatic Example**

Taking our security door example from above. Firstly we have the door interface and an implementation of door

```php
interface Door
{
    public function open();
    public function close();
}

class LabDoor implements Door
{
    public function open()
    {
        echo "Opening lab door";
    }

    public function close()
    {
        echo "Closing the lab door";
    }
}
```
Then we have a proxy to secure any doors that we want
```php
class SecuredDoor
{
    protected $door;

    public function __construct(Door $door)
    {
        $this->door = $door;
    }

    public function open($password)
    {
        if ($this->authenticate($password)) {
            $this->door->open();
        } else {
            echo "Big no! It ain't possible.";
        }
    }

    public function authenticate($password)
    {
        return $password === '$ecr@t';
    }

    public function close()
    {
        $this->door->close();
    }
}
```
And here is how it can be used
```php
$door = new SecuredDoor(new LabDoor());
$door->open('invalid'); // Big no! It ain't possible.

$door->open('$ecr@t'); // Opening lab door
$door->close(); // Closing lab door
```
Yet another example would be some sort of data-mapper implementation. For example, I recently made an ODM (Object Data Mapper) for MongoDB using this pattern where I wrote a proxy around mongo classes while utilizing the magic method `__call()`. All the method calls were proxied to the original mongo class and result retrieved was returned as it is but in case of `find` or `findOne` data was mapped to the required class objects and the object was returned instead of `Cursor`.

Behavioral Design Patterns
==========================

In plain words
> It is concerned with assignment of responsibilities between the objects. What makes them different from structural patterns is they don't just specify the structure but also outline the patterns for message passing/communication between them. Or in other words, they assist in answering "How to run a behavior in software component?"

Wikipedia says
> In software engineering, behavioral design patterns are design patterns that identify common communication patterns between objects and realize these patterns. By doing so, these patterns increase flexibility in carrying out this communication.

* [Chain of Responsibility](#-chain-of-responsibility)
* [Command](#-command)
* [Iterator](#-iterator)
* [Mediator](#-mediator)
* [Memento](#-memento)
* [Observer](#-observer)
* [Visitor](#-visitor)
* [Strategy](#-strategy)
* [State](#-state)
* [Template Method](#-template-method)

🔗 Chain of Responsibility
-----------------------

Real world example
> For example, you have three payment methods (`A`, `B` and `C`) setup in your account; each having a different amount in it. `A` has 100 USD, `B` has 300 USD and `C` having 1000 USD and the preference for payments is chosen as `A` then `B` then `C`. You try to purchase something that is worth 210 USD. Using Chain of Responsibility, first of all account `A` will be checked if it can make the purchase, if yes purchase will be made and the chain will be broken. If not, request will move forward to account `B` checking for amount if yes chain will be broken otherwise the request will keep forwarding till it finds the suitable handler. Here `A`, `B` and `C` are links of the chain and the whole phenomenon is Chain of Responsibility.

In plain words
> It helps building a chain of objects. Request enters from one end and keeps going from object to object till it finds the suitable handler.

Wikipedia says
> In object-oriented design, the chain-of-responsibility pattern is a design pattern consisting of a source of command objects and a series of processing objects. Each processing object contains logic that defines the types of command objects that it can handle; the rest are passed to the next processing object in the chain.

**Programmatic Example**

Translating our account example above. First of all we have a base account having the logic for chaining the accounts together and some accounts

```php
abstract class Account
{
    protected $successor;
    protected $balance;

    public function setNext(Account $account)
    {
        $this->successor = $account;
    }

    public function pay(float $amountToPay)
    {
        if ($this->canPay($amountToPay)) {
            echo sprintf('Paid %s using %s' . PHP_EOL, $amountToPay, get_called_class());
        } elseif ($this->successor) {
            echo sprintf('Cannot pay using %s. Proceeding ..' . PHP_EOL, get_called_class());
            $this->successor->pay($amountToPay);
        } else {
            throw new Exception('None of the accounts have enough balance');
        }
    }

    public function canPay($amount): bool
    {
        return $this->balance >= $amount;
    }
}

class Bank extends Account
{
    protected $balance;

    public function __construct(float $balance)
    {
        $this->balance = $balance;
    }
}

class Paypal extends Account
{
    protected $balance;

    public function __construct(float $balance)
    {
        $this->balance = $balance;
    }
}

class Bitcoin extends Account
{
    protected $balance;

    public function __construct(float $balance)
    {
        $this->balance = $balance;
    }
}
```

Now let's prepare the chain using the links defined above (i.e. Bank, Paypal, Bitcoin)

```php
// Let's prepare a chain like below
//      $bank->$paypal->$bitcoin
//
// First priority bank
//      If bank can't pay then paypal
//      If paypal can't pay then bit coin

$bank = new Bank(100);          // Bank with balance 100
$paypal = new Paypal(200);      // Paypal with balance 200
$bitcoin = new Bitcoin(300);    // Bitcoin with balance 300

$bank->setNext($paypal);
$paypal->setNext($bitcoin);

// Let's try to pay using the first priority i.e. bank
$bank->pay(259);

// Output will be
// ==============
// Cannot pay using bank. Proceeding ..
// Cannot pay using paypal. Proceeding ..:
// Paid 259 using Bitcoin!
```

👮 Command
-------

Real world example
> A generic example would be you ordering food at a restaurant. You (i.e. `Client`) ask the waiter (i.e. `Invoker`) to bring some food (i.e. `Command`) and waiter simply forwards the request to Chef (i.e. `Receiver`) who has the knowledge of what and how to cook.
> Another example would be you (i.e. `Client`) switching on (i.e. `Command`) the television (i.e. `Receiver`) using a remote control (`Invoker`).

In plain words
> Allows you to encapsulate actions in objects. The key idea behind this pattern is to provide the means to decouple client from receiver.

Wikipedia says
> In object-oriented programming, the command pattern is a behavioral design pattern in which an object is used to encapsulate all information needed to perform an action or trigger an event at a later time. This information includes the method name, the object that owns the method and values for the method parameters.

**Programmatic Example**

First of all we have the receiver that has the implementation of every action that could be performed
```php
// Receiver
class Bulb
{
    public function turnOn()
    {
        echo "Bulb has been lit";
    }

    public function turnOff()
    {
        echo "Darkness!";
    }
}
```
then we have an interface that each of the commands are going to implement and then we have a set of commands
```php
interface Command
{
    public function execute();
    public function undo();
    public function redo();
}

// Command
class TurnOn implements Command
{
    protected $bulb;

    public function __construct(Bulb $bulb)
    {
        $this->bulb = $bulb;
    }

    public function execute()
    {
        $this->bulb->turnOn();
    }

    public function undo()
    {
        $this->bulb->turnOff();
    }

    public function redo()
    {
        $this->execute();
    }
}

class TurnOff implements Command
{
    protected $bulb;

    public function __construct(Bulb $bulb)
    {
        $this->bulb = $bulb;
    }

    public function execute()
    {
        $this->bulb->turnOff();
    }

    public function undo()
    {
        $this->bulb->turnOn();
    }

    public function redo()
    {
        $this->execute();
    }
}
```
Then we have an `Invoker` with whom the client will interact to process any commands
```php
// Invoker
class RemoteControl
{
    public function submit(Command $command)
    {
        $command->execute();
    }
}
```
Finally let's see how we can use it in our client
```php
$bulb = new Bulb();

$turnOn = new TurnOn($bulb);
$turnOff = new TurnOff($bulb);

$remote = new RemoteControl();
$remote->submit($turnOn); // Bulb has been lit!
$remote->submit($turnOff); // Darkness!
```

Command pattern can also be used to implement a transaction based system. Where you keep maintaining the history of commands as soon as you execute them. If the final command is successfully executed, all good otherwise just iterate through the history and keep executing the `undo` on all the executed commands.

➿ Iterator
--------

Real world example
> An old radio set will be a good example of iterator, where user could start at some channel and then use next or previous buttons to go through the respective channels. Or take an example of MP3 player or a TV set where you could press the next and previous buttons to go through the consecutive channels or in other words they all provide an interface to iterate through the respective channels, songs or radio stations.  

In plain words
> It presents a way to access the elements of an object without exposing the underlying presentation.

Wikipedia says
> In object-oriented programming, the iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container's elements. The iterator pattern decouples algorithms from containers; in some cases, algorithms are necessarily container-specific and thus cannot be decoupled.

**Programmatic example**

In PHP it is quite easy to implement using SPL (Standard PHP Library). Translating our radio stations example from above. First of all we have `RadioStation`

```php
class RadioStation
{
    protected $frequency;

    public function __construct(float $frequency)
    {
        $this->frequency = $frequency;
    }

    public function getFrequency(): float
    {
        return $this->frequency;
    }
}
```
Then we have our iterator

```php
use Countable;
use Iterator;

class StationList implements Countable, Iterator
{
    /** @var RadioStation[] $stations */
    protected $stations = [];

    /** @var int $counter */
    protected $counter;

    public function addStation(RadioStation $station)
    {
        $this->stations[] = $station;
    }

    public function removeStation(RadioStation $toRemove)
    {
        $toRemoveFrequency = $toRemove->getFrequency();
        $this->stations = array_filter($this->stations, function (RadioStation $station) use ($toRemoveFrequency) {
            return $station->getFrequency() !== $toRemoveFrequency;
        });
    }

    public function count(): int
    {
        return count($this->stations);
    }

    public function current(): RadioStation
    {
        return $this->stations[$this->counter];
    }

    public function key()
    {
        return $this->counter;
    }

    public function next()
    {
        $this->counter++;
    }

    public function rewind()
    {
        $this->counter = 0;
    }

    public function valid(): bool
    {
        return isset($this->stations[$this->counter]);
    }
}
```
And then it can be used as
```php
$stationList = new StationList();

$stationList->addStation(new RadioStation(89));
$stationList->addStation(new RadioStation(101));
$stationList->addStation(new RadioStation(102));
$stationList->addStation(new RadioStation(103.2));

foreach($stationList as $station) {
    echo $station->getFrequency() . PHP_EOL;
}

$stationList->removeStation(new RadioStation(89)); // Will remove station 89
```

👽 Mediator
========

Real world example
> A general example would be when you talk to someone on your mobile phone, there is a network provider sitting between you and them and your conversation goes through it instead of being directly sent. In this case network provider is mediator.

In plain words
> Mediator pattern adds a third party object (called mediator) to control the interaction between two objects (called colleagues). It helps reduce the coupling between the classes communicating with each other. Because now they don't need to have the knowledge of each other's implementation.

Wikipedia says
> In software engineering, the mediator pattern defines an object that encapsulates how a set of objects interact. This pattern is considered to be a behavioral pattern due to the way it can alter the program's running behavior.

**Programmatic Example**

Here is the simplest example of a chat room (i.e. mediator) with users (i.e. colleagues) sending messages to each other.

First of all, we have the mediator i.e. the chat room

```php
interface ChatRoomMediator 
{
    public function showMessage(User $user, string $message);
}

// Mediator
class ChatRoom implements ChatRoomMediator
{
    public function showMessage(User $user, string $message)
    {
        $time = date('M d, y H:i');
        $sender = $user->getName();

        echo $time . '[' . $sender . ']:' . $message;
    }
}
```

Then we have our users i.e. colleagues
```php
class User {
    protected $name;
    protected $chatMediator;

    public function __construct(string $name, ChatRoomMediator $chatMediator) {
        $this->name = $name;
        $this->chatMediator = $chatMediator;
    }

    public function getName() {
        return $this->name;
    }

    public function send($message) {
        $this->chatMediator->showMessage($this, $message);
    }
}
```
And the usage
```php
$mediator = new ChatRoom();

$john = new User('John Doe', $mediator);
$jane = new User('Jane Doe', $mediator);

$john->send('Hi there!');
$jane->send('Hey!');

// Output will be
// Feb 14, 10:58 [John]: Hi there!
// Feb 14, 10:58 [Jane]: Hey!
```

💾 Memento
-------
Real world example
> Take the example of calculator (i.e. originator), where whenever you perform some calculation the last calculation is saved in memory (i.e. memento) so that you can get back to it and maybe get it restored using some action buttons (i.e. caretaker).

In plain words
> Memento pattern is about capturing and storing the current state of an object in a manner that it can be restored later on in a smooth manner.

Wikipedia says
> The memento pattern is a software design pattern that provides the ability to restore an object to its previous state (undo via rollback).

Usually useful when you need to provide some sort of undo functionality.

**Programmatic Example**

Lets take an example of text editor which keeps saving the state from time to time and that you can restore if you want.

First of all we have our memento object that will be able to hold the editor state

```php
class EditorMemento
{
    protected $content;

    public function __construct(string $content)
    {
        $this->content = $content;
    }

    public function getContent()
    {
        return $this->content;
    }
}
```

Then we have our editor i.e. originator that is going to use memento object

```php
class Editor
{
    protected $content = '';

    public function type(string $words)
    {
        $this->content = $this->content . ' ' . $words;
    }

    public function getContent()
    {
        return $this->content;
    }

    public function save()
    {
        return new EditorMemento($this->content);
    }

    public function restore(EditorMemento $memento)
    {
        $this->content = $memento->getContent();
    }
}
```

And then it can be used as

```php
$editor = new Editor();

// Type some stuff
$editor->type('This is the first sentence.');
$editor->type('This is second.');

// Save the state to restore to : This is the first sentence. This is second.
$saved = $editor->save();

// Type some more
$editor->type('And this is third.');

// Output: Content before Saving
echo $editor->getContent(); // This is the first sentence. This is second. And this is third.

// Restoring to last saved state
$editor->restore($saved);

$editor->getContent(); // This is the first sentence. This is second.
```

😎 Observer
--------
Real world example
> A good example would be the job seekers where they subscribe to some job posting site and they are notified whenever there is a matching job opportunity.   

In plain words
> Defines a dependency between objects so that whenever an object changes its state, all its dependents are notified.

Wikipedia says
> The observer pattern is a software design pattern in which an object, called the subject, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.

**Programmatic example**

Translating our example from above. First of all we have job seekers that need to be notified for a job posting
```php
class JobPost
{
    protected $title;

    public function __construct(string $title)
    {
        $this->title = $title;
    }

    public function getTitle()
    {
        return $this->title;
    }
}

class JobSeeker implements Observer
{
    protected $name;

    public function __construct(string $name)
    {
        $this->name = $name;
    }

    public function onJobPosted(JobPost $job)
    {
        // Do something with the job posting
        echo 'Hi ' . $this->name . '! New job posted: '. $job->getTitle();
    }
}
```
Then we have our job postings to which the job seekers will subscribe
```php
class EmploymentAgency implements Observable
{
    protected $observers = [];

    protected function notify(JobPost $jobPosting)
    {
        foreach ($this->observers as $observer) {
            $observer->onJobPosted($jobPosting);
        }
    }

    public function attach(Observer $observer)
    {
        $this->observers[] = $observer;
    }

    public function addJob(JobPost $jobPosting)
    {
        $this->notify($jobPosting);
    }
}
```
Then it can be used as
```php
// Create subscribers
$johnDoe = new JobSeeker('John Doe');
$janeDoe = new JobSeeker('Jane Doe');

// Create publisher and attach subscribers
$jobPostings = new EmploymentAgency();
$jobPostings->attach($johnDoe);
$jobPostings->attach($janeDoe);

// Add a new job and see if subscribers get notified
$jobPostings->addJob(new JobPost('Software Engineer'));

// Output
// Hi John Doe! New job posted: Software Engineer
// Hi Jane Doe! New job posted: Software Engineer
```

🏃 Visitor
-------
Real world example
> Consider someone visiting Dubai. They just need a way (i.e. visa) to enter Dubai. After arrival, they can come and visit any place in Dubai on their own without having to ask for permission or to do some leg work in order to visit any place here; just let them know of a place and they can visit it. Visitor pattern lets you do just that, it helps you add places to visit so that they can visit as much as they can without having to do any legwork.

In plain words
> Visitor pattern lets you add further operations to objects without having to modify them.

Wikipedia says
> In object-oriented programming and software engineering, the visitor design pattern is a way of separating an algorithm from an object structure on which it operates. A practical result of this separation is the ability to add new operations to existing object structures without modifying those structures. It is one way to follow the open/closed principle.

**Programmatic example**

Let's take an example of a zoo simulation where we have several different kinds of animals and we have to make them Sound. Let's translate this using visitor pattern

```php
// Visitee
interface Animal
{
    public function accept(AnimalOperation $operation);
}

// Visitor
interface AnimalOperation
{
    public function visitMonkey(Monkey $monkey);
    public function visitLion(Lion $lion);
    public function visitDolphin(Dolphin $dolphin);
}
```
Then we have our implementations for the animals
```php
class Monkey implements Animal
{
    public function shout()
    {
        echo 'Ooh oo aa aa!';
    }

    public function accept(AnimalOperation $operation)
    {
        $operation->visitMonkey($this);
    }
}

class Lion implements Animal
{
    public function roar()
    {
        echo 'Roaaar!';
    }

    public function accept(AnimalOperation $operation)
    {
        $operation->visitLion($this);
    }
}

class Dolphin implements Animal
{
    public function speak()
    {
        echo 'Tuut tuttu tuutt!';
    }

    public function accept(AnimalOperation $operation)
    {
        $operation->visitDolphin($this);
    }
}
```
Let's implement our visitor
```php
class Speak implements AnimalOperation
{
    public function visitMonkey(Monkey $monkey)
    {
        $monkey->shout();
    }

    public function visitLion(Lion $lion)
    {
        $lion->roar();
    }

    public function visitDolphin(Dolphin $dolphin)
    {
        $dolphin->speak();
    }
}
```

And then it can be used as
```php
$monkey = new Monkey();
$lion = new Lion();
$dolphin = new Dolphin();

$speak = new Speak();

$monkey->accept($speak);    // Ooh oo aa aa!    
$lion->accept($speak);      // Roaaar!
$dolphin->accept($speak);   // Tuut tutt tuutt!
```
We could have done this simply by having an inheritance hierarchy for the animals but then we would have to modify the animals whenever we would have to add new actions to animals. But now we will not have to change them. For example, let's say we are asked to add the jump behavior to the animals, we can simply add that by creating a new visitor i.e.

```php
class Jump implements AnimalOperation
{
    public function visitMonkey(Monkey $monkey)
    {
        echo 'Jumped 20 feet high! on to the tree!';
    }

    public function visitLion(Lion $lion)
    {
        echo 'Jumped 7 feet! Back on the ground!';
    }

    public function visitDolphin(Dolphin $dolphin)
    {
        echo 'Walked on water a little and disappeared';
    }
}
```
And for the usage
```php
$jump = new Jump();

$monkey->accept($speak);   // Ooh oo aa aa!
$monkey->accept($jump);    // Jumped 20 feet high! on to the tree!

$lion->accept($speak);     // Roaaar!
$lion->accept($jump);      // Jumped 7 feet! Back on the ground!

$dolphin->accept($speak);  // Tuut tutt tuutt!
$dolphin->accept($jump);   // Walked on water a little and disappeared
```

💡 Strategy
--------

Real world example
> Consider the example of sorting, we implemented bubble sort but the data started to grow and bubble sort started getting very slow. In order to tackle this we implemented Quick sort. But now although the quick sort algorithm was doing better for large datasets, it was very slow for smaller datasets. In order to handle this we implemented a strategy where for small datasets, bubble sort will be used and for larger, quick sort.

In plain words
> Strategy pattern allows you to switch the algorithm or strategy based upon the situation.

Wikipedia says
> In computer programming, the strategy pattern (also known as the policy pattern) is a behavioural software design pattern that enables an algorithm's behavior to be selected at runtime.

**Programmatic example**

Translating our example from above. First of all we have our strategy interface and different strategy implementations

```php
interface SortStrategy
{
    public function sort(array $dataset): array;
}

class BubbleSortStrategy implements SortStrategy
{
    public function sort(array $dataset): array
    {
        echo "Sorting using bubble sort";

        // Do sorting
        return $dataset;
    }
}

class QuickSortStrategy implements SortStrategy
{
    public function sort(array $dataset): array
    {
        echo "Sorting using quick sort";

        // Do sorting
        return $dataset;
    }
}
```

And then we have our client that is going to use any strategy
```php
class Sorter
{
    protected $sorter;

    public function __construct(SortStrategy $sorter)
    {
        $this->sorter = $sorter;
    }

    public function sort(array $dataset): array
    {
        return $this->sorter->sort($dataset);
    }
}
```
And it can be used as
```php
$dataset = [1, 5, 4, 3, 2, 8];

$sorter = new Sorter(new BubbleSortStrategy());
$sorter->sort($dataset); // Output : Sorting using bubble sort

$sorter = new Sorter(new QuickSortStrategy());
$sorter->sort($dataset); // Output : Sorting using quick sort
```

💢 State
-----
Real world example
> Imagine you are using some drawing application, you choose the paint brush to draw. Now the brush changes its behavior based on the selected color i.e. if you have chosen red color it will draw in red, if blue then it will be in blue etc.  

In plain words
> It lets you change the behavior of a class when the state changes.

Wikipedia says
> The state pattern is a behavioral software design pattern that implements a state machine in an object-oriented way. With the state pattern, a state machine is implemented by implementing each individual state as a derived class of the state pattern interface, and implementing state transitions by invoking methods defined by the pattern's superclass.
> The state pattern can be interpreted as a strategy pattern which is able to switch the current strategy through invocations of methods defined in the pattern's interface.

**Programmatic example**

Let's take an example of text editor, it lets you change the state of text that is typed i.e. if you have selected bold, it starts writing in bold, if italic then in italics etc.

First of all we have our state interface and some state implementations

```php
interface WritingState
{
    public function write(string $words);
}

class UpperCase implements WritingState
{
    public function write(string $words)
    {
        echo strtoupper($words);
    }
}

class LowerCase implements WritingState
{
    public function write(string $words)
    {
        echo strtolower($words);
    }
}

class DefaultText implements WritingState
{
    public function write(string $words)
    {
        echo $words;
    }
}
```
Then we have our editor
```php
class TextEditor
{
    protected $state;

    public function __construct(WritingState $state)
    {
        $this->state = $state;
    }

    public function setState(WritingState $state)
    {
        $this->state = $state;
    }

    public function type(string $words)
    {
        $this->state->write($words);
    }
}
```
And then it can be used as
```php
$editor = new TextEditor(new DefaultText());

$editor->type('First line');

$editor->setState(new UpperCase());

$editor->type('Second line');
$editor->type('Third line');

$editor->setState(new LowerCase());

$editor->type('Fourth line');
$editor->type('Fifth line');

// Output:
// First line
// SECOND LINE
// THIRD LINE
// fourth line
// fifth line
```

📒 Template Method
---------------

Real world example
> Suppose we are getting some house built. The steps for building might look like
> - Prepare the base of house
> - Build the walls
> - Add roof
> - Add other floors

> The order of these steps could never be changed i.e. you can't build the roof before building the walls etc but each of the steps could be modified for example walls can be made of wood or polyester or stone.

In plain words
> Template method defines the skeleton of how a certain algorithm could be performed, but defers the implementation of those steps to the children classes.

Wikipedia says
> In software engineering, the template method pattern is a behavioral design pattern that defines the program skeleton of an algorithm in an operation, deferring some steps to subclasses. It lets one redefine certain steps of an algorithm without changing the algorithm's structure.

**Programmatic Example**

Imagine we have a build tool that helps us test, lint, build, generate build reports (i.e. code coverage reports, linting report etc) and deploy our app on the test server.

First of all we have our base class that specifies the skeleton for the build algorithm
```php
abstract class Builder
{

    // Template method
    final public function build()
    {
        $this->test();
        $this->lint();
        $this->assemble();
        $this->deploy();
    }

    abstract public function test();
    abstract public function lint();
    abstract public function assemble();
    abstract public function deploy();
}
```

Then we can have our implementations

```php
class AndroidBuilder extends Builder
{
    public function test()
    {
        echo 'Running android tests';
    }

    public function lint()
    {
        echo 'Linting the android code';
    }

    public function assemble()
    {
        echo 'Assembling the android build';
    }

    public function deploy()
    {
        echo 'Deploying android build to server';
    }
}

class IosBuilder extends Builder
{
    public function test()
    {
        echo 'Running ios tests';
    }

    public function lint()
    {
        echo 'Linting the ios code';
    }

    public function assemble()
    {
        echo 'Assembling the ios build';
    }

    public function deploy()
    {
        echo 'Deploying ios build to server';
    }
}
```
And then it can be used as

```php
$androidBuilder = new AndroidBuilder();
$androidBuilder->build();

// Output:
// Running android tests
// Linting the android code
// Assembling the android build
// Deploying android build to server

$iosBuilder = new IosBuilder();
$iosBuilder->build();

// Output:
// Running ios tests
// Linting the ios code
// Assembling the ios build
// Deploying ios build to server
```

## 🚦 Wrap Up Folks

And that about wraps it up. I will continue to improve this, so you might want to watch/star this repository to revisit. Also, I have plans on writing the same about the architectural patterns, stay tuned for it.

## 👬 Contribution

- Report issues
- Open pull request with improvements
- Spread the word
- Reach out with any feedback [![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/kamranahmedse.svg?style=social&label=Follow%20%40kamranahmedse)](https://twitter.com/kamranahmedse)

## License

[![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by/4.0/)
