# Spring

## 1. 优点

* Spring是一个开源的免费框架
* Spring是一个轻量级、非入侵的框架
* 控制反转（IOC），面向切面编程（AOP）
* 支持事务的处理，对框架整合的支持

In a word，Spring就是一个轻量级的控制反转（IOC）和面向切面编程（AOP）框架。

![img](https://images2017.cnblogs.com/blog/1219227/201709/1219227-20170930225010356-45057485.gif)

## 2. IOC理论推导

1. UserDao接口
2. UserDaoImpl实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

```java
private UserDao userDao = new UserDaoImpl();
```

在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改源代码！如果程序代码量非常大，修改一次的成本非常昂贵！

如果我们使用set注入，则发生了革命性的变化

```java
private UserDao userDao;
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

* 之前是程序主动创建对象，控制权在程序员手上
* set注入后，程序不再有主动性，而是变成了被动接受对象

![image-20200428211603381](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge9sktzwslj30lg0mq0wp.jpg)

这种思想，从本质上解决了问题，码农不用再去管理对象的创建，系统的耦合性大大降低，可以更加专注在业务实现上。这是IOC的原型

## 3. IOC的本质

控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象是由Spring来创建

反转：程序本身不创建对象，而变成被动的接收对象

依赖注入：就是利用set方法来进行注入

IOC是一种编程思想，由主动的编程变成被动的接收，所谓的IOC，一句话搞定：对象由Spring来创建，管理和装配。

## 4. IOC创建对象的方式

1. 使用无参构造方法创建对象，默认！

2. 有参构造创建对象

   1. 下标赋值

   ```xml
   <!--第一种，下标赋值-->
   <bean id="user" class="com.kuang.pojo.user">
   	<constructor-arg index="0" value="狂神说java"
   </bean>
   ```

   2. 参数名

   ```xml
   <bean id="user" class="io.github.ericwu0930.pojo.user">
       <constructor-arg name="name" value="秦疆"/>
   </bean>
   ```

## 5.  依赖注入

看spring-04-di

### 5.1 构造器注入

Example 1:

```java
package x.y;

public class ThingOne {

    public ThingOne(ThingTwo thingTwo, ThingThree thingThree) {
        // ...
    }
}
```

通过构造器对类进行注入

```xml
<beans>
    <bean id="beanOne" class="x.y.ThingOne">
        <!--引用其他bean-->
        <constructor-arg ref="beanTwo"/>
        <constructor-arg ref="beanThree"/>
    </bean>

    <bean id="beanTwo" class="x.y.ThingTwo"/>

    <bean id="beanThree" class="x.y.ThingThree"/>
    
</beans>
```

Example 2:

```java
package examples;

public class ExampleBean {

    // Number of years to calculate the Ultimate Answer
    private int years;

    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```

```xml
<!--根据type对成员变量进行注入-->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
</bean>

<!--根据index对成员变量进行注入-->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
</bean>

<!--根据成员变量的名字进行注入-->
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
</bean>

```

### 5.2 Set方式注入

Example

```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setIntegerProperty(int i) {
        this.i = i;
    }
}
```

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- setter injection using the nested ref element -->
    <property name="beanOne">
        <ref bean="anotherExampleBean"/>
    </property>

    <!-- setter injection using the neater ref attribute -->
    <property name="beanTwo" ref="yetAnotherBean"/>
    <property name="integerProperty" value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

### 5.3 拓展方式注入

* p命名空间和c命名空间需要添加约束

```xml
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"
```

### 5.4 bean的作用域

![image-20200428100237753](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge9qlcpalzj31i70c1jto.jpg)

1. 单例模式（默认机制）

   ```xml
   <bean id="user" class="io.github.ericwu0930.pojo.User" p:name="吴昊" p:age="18" scope="singleton"/>
   ```

2. 原型模式：每次从容器中get的时候，都是一个新的对象

   ```xml
   <bean id="user" class="io.github.ericwu0930.pojo.User" p:name="吴昊" p:age="18" scope="prototype"/>
   ```

3. 其余的request、session、application这些个只能在web开发中使用

## 6. Bean的自动装配

* 自动装配是Spring满足bean依赖的一种方式
* Spring会在上下文中自动寻找，并自动给bean装配属性。

在Spring中有三种装配方式

1. 在xml中显式配置
2. 在java中显式装配
3. 隐式的自动装配bean【重要】

### 6.1 byName自动装配

```xml
<!--  byName:会自动在容器上下文中查找，和自己对象set方法后面的值对应的beanid！-->
    <bean id="people" class="io.github.ericwu0930.pojo.People" autowire="byName">
        <property name="name" value="eric"/>
    </bean>
```

### 6.2 byType自动装配

```xml
<!--  byType:会自动在容器上下文中查找，和自己对象属性类型相同的bean-->
    <bean id="people" class="io.github.ericwu0930.pojo.People" autowire="byType">
        <property name="name" value="eric"/>
    </bean>
```

小结：

* byName的时候，需要保证所有的bean的id唯一，并且这个bean需要和自动注入属性的set方法的值一致
* byType的时候，需要保证所有的bean的class唯一，并且这个bean需要和自动注入的属性类型一致

### 6.3  使用注解实现自动装配

要使用注解须知：

1. 导入约束         xmlns:context="http://www.springframework.org/schema/context"
2. 配置注解的支持    <context:annotation-config/>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

**@Autowired**

可以在属性上使用，也可以在set方式上使用

使用Autowired我们可以不再编写Set方法，前提是你这个自动装配的属性在IOC容器中存在，且符合名字byType（先按照byType匹配，再使用byName匹配）

如果使用@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用**@Qualifier(value="xxx")**来配合@Autowired的使用，指定唯一的bean对象的注入

**@Resource**

自动装配，优先通过byName，再通过byType

## 7. 使用注解开发

对应于spring-06-annotation

1. bean

   **@Component**

   组件，放在类上，说明这个类被Spring管理了，就是bean！

2. 属性如何注入

   **@Value**

   属性的注入，类似于<property name="name" value="eric"/>

3. 衍生的注解

   @Component有几个衍生注解，我们在web开发中，会按照mvc三层架构分层！

   * dao            【@Repository】
   * service        【@Service】
   * controller     【@Controller】

4. 自动装配（Chapter6已讲）

5. 作用域

   @Scope("singleton")

   @Scope("prototype")

6. 小结

   xml更加万能，适用于任何场合，维护简单方便；注解维护相对复杂，不是自己的类使用不了。

   xml与注解最佳实践：xml用来管理bean；注解之复杂而完成属性的注入；

   ```xml
   <!--  指定要扫描的包，这个包下的注解会生效  -->
       <context:component-scan base-package="io.github.ericwu0930"/>
       <context:annotation-config/>
   ```


## 8. 代理模式

Spring AOP的底层。

代理模式分类：

* 静态代理 
* 动态代理

### 8.1 静态代理额

![企业微信截图_15880804648754](https://tva1.sinaimg.cn/large/007S8ZIlgy1ge9wgp25z2j30l109u74r.jpg)

角色分析：

* 抽象角色：一般会使用接口或者抽象类来解决
* 真实角色：被代理的角色
* 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
* 客户：访问代理对象的人

代码步骤：

* 接口
* 真实角色
* 代理角色
* 客户端访问代理角色

代理模式的好处：

* 可以使真实角色的操作更加纯粹！不用关注一些公共业务
* 公共业务交给代理角色，实现了业务的分工
* 公共业务发生扩展的时候，方便集中管理

![image-20200428233247117](https://tva1.sinaimg.cn/large/007S8ZIlgy1gee1pgbp68j30uo0owmyl.jpg)

### 8.2 动态代理

* 动态代理和静态代理角色一样
* 动态代理的代理类是动态生成的，不是我们直接写好的
* 动态代理分为两大类：基于接口的动态代理，基于类的动态代理

需要了解两个类：Proxy代理，InvocationHandler调用处理程序

```java
//InvocationHandler中只有一个方法
Object invoke(Object proxy, Method method, Object[] args);
//proxy为调用方法的代理对象
//method是调用方法的一个反射
//args则是调用方法的参数
//InvocationHandler将请求转发给RealSubject,因此实现InvocationHandler的类中应该持有被代理的对象
```

Proxy提供了用于创建动态代理实例的静态方法。该类中使用一个静态方法newProxyInstance返回代理对象。该方法需要传入三个参数。

```java
public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
//loader为一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
//interfaces:为一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
//h为一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
```

设计动态代理类

```java
public class ProxyInvocationHandler implements InvocationHandler {
    
    private Rent rent;
    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                                      rent.getClass().getInterfaces(),this);
    }
    
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seeHouse();
        fare();
        Object invoke = method.invoke(rent, args);
        return invoke;
    }

    public void seeHouse(){
        System.out.println("中介带看");
    }

    public void fare(){
        System.out.println("收中介费");
    }
}
```

动态代理的好处：一个动态代理类代理的是一个接口，一般对应一类业务（可以同时代理多个具有相同接口的类）

## 9. AOP

AOP：面向切面编程，关于AOP的一些[概念][1]

### 9.1 使用Spring实现AOP

导入Maven依赖

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.5</version>
</dependency>
```

方法一：使用Spring API接口

方式二：使用自定义类来实现AOP

方式三：使用注解实现

[1]: https://www.jianshu.com/p/27cc6a3bcab6

## 10. Mybatis与Spring的整合

### 10.1 回忆Mybatis

1. 编写实体类

2. 编写核心配置文件

3. 编写接口

4. 编写Mapper.xml

5. 测试

   ```java
   1. 使用Resources.getResourceAsStream("mybatis-config.xml")将配置文件读为流
   2. new SqlSessionFactoryBuilder().build(resourceAsStream);新建SqlSessionFactory
   3. 新建一个SqlSession
   4. 使用sqlSession与之前写好的dao接口绑定
   5. 调用dao接口的方法
   ```

### 10.2 Mybatis-Spring

步骤：

1. 编写数据源配置
2. sqlSessionFactory
3. sqlSessionTemplate
4. 需要给接口添加实现类
5. 测试

可以发现，Mybatis与Spring的整合，需要额外写一个Spring的xml文件，如下所示，以及一个Dao接口的实现类

```xml
<!--  DataSource:使用Spring的数据源替换Mybatis的配置 我们这里使用Spring提供的JDBC-->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=UTF-8"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>
<!--  sqlSessionFactory  -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 绑定Mybatis配置文件 -->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:io/github/ericwu0930/mapper/*.xml"/>
    </bean>
<!--  就是我们的sqlSession  -->
<!--   只能使用构造方法注入，因为没有set方法    -->     
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userMapper" class="io.github.ericwu0930.mapper.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
```

```java
public class UserMapperImpl implements UserMapper {
    // 我们所有的操作，都是用sqlSession来执行，在原来，现在都是用SqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        return sqlSession.getMapper(UserMapper.class).selectUser();
    }
}
```

## 11. 声明式事物

回顾事物

* 要么都成功，要么都失败，把一组业务当做一个业务来做
* 在项目开发中，十分的重要，设计到数据的一致性问题，不能马虎
* 确保完整性和一致性

事物的ACID原则：

* 原子性
* 一致性
* 隔离性
  * 多个业务可能操作同一个资源，放置数据损坏
* 持久性
  * 事物一旦提交，无论系统发生什么问题，结果都不会再被影响，被持久化的写到存储器中