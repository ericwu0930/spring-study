package io.github.ericwu0930.mapper;

import io.github.ericwu0930.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * @author erichwu
 * @date 2020/4/30
 */
public class UserMapperImpl implements UserMapper {
    // 我们所有的操作，都是用sqlSession来执行，在原来，现在都是用SqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        return sqlSession.getMapper(UserMapper.class).selectUser();
    }
}
