import io.github.ericwu0930.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        People people = classPathXmlApplicationContext.getBean("people", People.class);
        System.out.println(people);
        people.getCat().shout();
        people.getDog().shout();
    }
}
