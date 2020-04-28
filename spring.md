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

![image-20200428100237753](D:\实习期间学习笔记\img\image-20200428100237753.png)

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

@Autowired

可以在属性上使用，也可以在set方式上使用

使用Autowired我们可以不再编写Set方法，前提是你这个自动装配的属性在IOC容器中存在，且符合名字byType（先按照byType匹配，再使用byName匹配）

如果使用@Autowired自动装配的环境比较复杂，自动装配无法通过一个注解【@Autowired】完成的时候，我们可以使用@Qualifier(value="xxx")来配合@Autowired的使用，指定唯一的bean对象的注入
