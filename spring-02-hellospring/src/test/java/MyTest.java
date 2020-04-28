import io.github.ericwu0930.pojo.Hello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void testHello(){
        //获取Spring的上下文对象
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象都在Spring中管理了，我们要使用，直接从里面取出来就可以
        Hello hello = (Hello)classPathXmlApplicationContext.getBean("hello");
        System.out.println(hello);
    }
}
