package com.carl.ioc.framework.context.support;

import com.carl.ioc.framework.beans.BeanDefinition;
import com.carl.ioc.framework.beans.support.BeanDefinitionReader;
import com.carl.ioc.framework.beans.support.BeanDefinitionRegistry;
import com.carl.ioc.framework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringDemo
 * @description: ApplicationContext接口的子实现类, 用于立即加载
 * @author: Mr.Carl
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {
    //声明解析器变量
    protected BeanDefinitionReader beanDefinitionReader;
    //用来存储bean对象的容器   key存储的是bean的id值，value存储的是bean对象
    protected Map<String, Object> singletonObjects = new HashMap<>();

    //存储配置文件的路径
    protected String configLocation;

    public void refresh() throws IllegalStateException, Exception {

        //加载BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(configLocation);

        //初始化bean
        finishBeanInitialization();
    }

    //bean的初始化
    private void finishBeanInitialization() throws Exception {
        //获取注册表对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        //获取beanDefinition对象
        String[] beanNames = registry.getBeanDefinitionNames();
        //进行bean的初始化
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            getBean(beanName);
        }
    }
}
