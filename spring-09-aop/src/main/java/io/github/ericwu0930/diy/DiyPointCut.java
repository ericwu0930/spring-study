package io.github.ericwu0930.diy;

/**
 * @author erichwu
 * @date 2020/4/29
 */
public class DiyPointCut {
    public void before(){
        System.out.println("=========方法执行前");
    }
    public void after(){
        System.out.println("==========方法执行后");
    }
}
