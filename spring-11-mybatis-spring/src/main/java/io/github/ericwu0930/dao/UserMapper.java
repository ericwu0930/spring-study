package io.github.ericwu0930.dao;

import io.github.ericwu0930.pojo.User;

import java.util.List;

/**
 * @author erichwu
 * @date 2020/4/30
 */
public interface UserMapper {
    public List<User> selectUser();
}
