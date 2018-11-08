package com.demo.tspring.EEBook1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 切面
 * @Author: CaoXiaoLong create on 2017/7/19 11:41.
 */
@Aspect  //生命切面
@Component
public class LogAspect {

    @Pointcut("@annotation(com.demo.tspring.EEBook1.Action)") //切入点
    public void annotationPointCut(){

    }

    @After("annotationPointCut()") //声明一个建言 使用@PointCut定义的切点
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截"+action.name()); //通过反射获取注解的属性
    }

    @Before("execution(* com.demo.tspring.EEBook1.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则式拦截"+method.getName());
    }
}
