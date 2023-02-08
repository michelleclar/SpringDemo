package com.carl.ioc.framework.beans.support.impl;

import com.carl.ioc.framework.beans.BeanDefinition;
import com.carl.ioc.framework.beans.support.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringDemo
 * @description: 注册表接口的子实现类
 * @author: Mr.Carl
 **/
public class SimpleBeanDefinitionRegistry implements BeanDefinitionRegistry {

    //定义一个容器,用来存储BeanDefinition
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    //注册bean
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    //移除bean
    public void removeBeanDefinition(String beanName) throws Exception {
        beanDefinitionMap.remove(beanName);
    }

    //通过名字获取bean
    public BeanDefinition getBeanDefinition(String beanName) throws Exception {
        return beanDefinitionMap.get(beanName);
    }

    //是否包含
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    //容器大小
    public int getBeanDefinitionCount() {
        return beanDefinitionMap.size();
    }

    //所有键数组
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[1]);
    }
}
