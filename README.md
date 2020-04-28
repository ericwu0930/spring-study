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

### 5.2 Set方式注入

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

![image-20200428233247117](../../Library/Application Support/typora-user-images/image-20200428233247117.png)

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

