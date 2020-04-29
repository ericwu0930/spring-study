package io.github.ericwu0930.diy;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author erichwu
 * @date 2020/4/29
 */

@Aspect  //标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* io.github.ericwu0930.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("======在方法执行之前");
    }
}
