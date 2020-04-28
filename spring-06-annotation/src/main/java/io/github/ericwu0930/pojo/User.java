package io.github.ericwu0930.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于 <bean id="user" class="io.github.ericwu0930.pojo.User">
// @Component 注解

@Component
@Scope("singleton")
public class User {

    // 相当于<property name="name" value="eric"/>
    // 复杂的注入使用xml
    @Value("eric")
    public String name;
}
