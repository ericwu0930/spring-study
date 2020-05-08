package io.github.ericwu0930.mapper;

import io.github.ericwu0930.pojo.User;

import java.util.List;

/**
 * @author erichwu
 * @date 2020/4/30
 */
public interface UserMapper {
    public List<User> selectUser();
    //添加一个用户
    public int addUser(User user);
    //删除一个用户
    public int deleteUser(int id);
}
