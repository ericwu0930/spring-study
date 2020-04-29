import io.github.ericwu0930.service.Userservice;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testLog(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 动态代理代理的是接口
        Userservice userService = classPathXmlApplicationContext.getBean("userService", Userservice.class);
        userService.add();
    }
}
