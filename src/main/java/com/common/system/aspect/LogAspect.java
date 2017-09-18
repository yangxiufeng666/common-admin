package com.common.system.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p>记录操作数据库的日志</p>
 * Created by Mr.Yangxiufeng on 2017/9/14.
 * Time:17:30
 * ProjectName:Common-admin
 */
@Aspect
@Component
public class LogAspect {

    public void update(){

    }
//    @Pointcut("execution(* com.common.system.service.impl.*.*(..))")
    public void select(){
        System.out.println("...........................................................");
    }
//    @AfterReturning(value = "select()")
    public void select1(){
        System.out.println("...........................................................");
    }
}
