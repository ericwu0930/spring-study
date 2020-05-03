package io.github.ericwu0930.mapper;

import io.github.ericwu0930.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author erichwu
 * @date 2020/4/30
 */
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{
    // 我们所有的操作，都是用sqlSession来执行，在原来，现在都是用SqlSessionTemplate

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }

    public List<User> selectUser() {
        User user = new User(6, "小王", "2132131");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.deleteUser(5);
        return mapper.selectUser();
    }
}
