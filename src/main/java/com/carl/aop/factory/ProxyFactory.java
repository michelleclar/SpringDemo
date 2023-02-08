package com.carl.aop.factory;

import lombok.Getter;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: SpringDemo
 * @description: 动态代理
 * @author: Mr.Carl
 **/
public class ProxyFactory {
    //代理对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //返回代理对象
    public Object getProxy() {
        //类加载器,接口,invocationHandler
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    //方法执行前
                    before(proxy,method,args);
                    result = method.invoke(args);
                    //方法执行后
                    after(proxy,method,args);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                return result;
            }
        };

        return Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
    }

    public void before(Object proxy, Method method, Object[] args) {

    }

    public void after(Object proxy, Method method, Object[] args) {

    }
}
