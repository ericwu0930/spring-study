import io.github.ericwu0930.pojo.Student;
import io.github.ericwu0930.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testStudent(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) classPathXmlApplicationContext.getBean("student");
        System.out.println(student);
    }

    @Test
    public void testPNameSpace(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("userBeans.xml");
        User user = (User) classPathXmlApplicationContext.getBean("user");
        User user1 = classPathXmlApplicationContext.getBean("user", User.class);
        System.out.println(user);
        System.out.println(user==user1);
    }
}
