import io.github.ericwu0930.service.UserService;
import io.github.ericwu0930.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testGetUser(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl = (UserServiceImpl) classPathXmlApplicationContext.getBean("userServiceImpl");

        userServiceImpl.getUser();
    }
}
