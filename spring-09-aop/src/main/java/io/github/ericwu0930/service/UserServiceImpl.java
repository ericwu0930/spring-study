package io.github.ericwu0930.service;

public class UserServiceImpl implements Userservice{
    public void add() {
        System.out.println("添加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void select() {
        System.out.println("查询了一个用户");
    }

    public void update() {
        System.out.println("更新了一个用户");
    }
}
