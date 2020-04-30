import io.github.ericwu0930.mapper.UserMapper;
import io.github.ericwu0930.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author erichwu
 * @date 2020/4/30
 */
public class MyTest {
    @Test
    public void test() throws IOException {
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper = classPathXmlApplicationContext.getBean("userMapper", UserMapper.class);
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
