import io.github.ericwu0930.mapper.UserMapper;
import io.github.ericwu0930.mapper.UserMapperImpl;
import io.github.ericwu0930.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author erichwu
 * @date 2020/5/2
 */
public class MyTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper = classPathXmlApplicationContext.getBean("userMapper", UserMapper.class);
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
