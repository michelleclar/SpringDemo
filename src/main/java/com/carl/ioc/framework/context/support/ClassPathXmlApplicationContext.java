package com.carl.ioc.framework.context.support;

import com.carl.ioc.framework.beans.BeanDefinition;
import com.carl.ioc.framework.beans.MutableProperty;
import com.carl.ioc.framework.beans.Property;
import com.carl.ioc.framework.beans.support.BeanDefinitionRegistry;
import com.carl.ioc.framework.beans.xml.XmlBeanDefinitionReader;
import com.carl.ioc.framework.utils.StringUtils;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @program: SpringDemo
 * @description: IoC容器具体的子实现类
 * 用于加载类路径下的xml格式的配置文件
 * @author: Mr.Carl
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    public ClassPathXmlApplicationContext(String configLocation){
        this.configLocation = configLocation;
        //构建解析器对象
        beanDefinitionReader = new XmlBeanDefinitionReader();
        try {
            this.refresh();
        } catch (Exception e) {
        }
    }

    //根据bean的id属性值获取bean对象
    public Object getBean(String name) throws Exception {

        //判断对象容器中是否包含指定名称的bean对象,如果包含直接返回,如果不包含,需要自行创建
        Object obj = singletonObjects.get(name);
        if(obj != null) {
            return obj;
        }
        //获取BeanDefinition对象
        BeanDefinitionRegistry registry = beanDefinitionReader.getRegistry();
        BeanDefinition beanDefinition = registry.getBeanDefinition(name);
        if(beanDefinition == null) {
            return null;
        }
        //获取bean信息中的className
        String className = beanDefinition.getClassName();
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getConstructor();

        //Object beanObj = clazz.newInstance();
        Object beanObj = clazz.getConstructor().newInstance();

        //进行依赖注入
        MutableProperty propertyValues = beanDefinition.getPropertyValues();
        for (Property propertyValue : propertyValues) {
            //获取name属性值
            String propertyName = propertyValue.getName();
            //获取value
            String value = propertyValue.getValue();
            //获取ref
            String ref = propertyValue.getRef();
            if(ref != null && !"".equals(ref)) {
                //获取依赖bean对象
                Object bean = getBean(ref);
                //拼接方法名
                String methodName = StringUtils.getSetterMethodNameByFieldName(propertyName);
                //获取所有的方法名
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(method.getName().equals(methodName)) {
                        //setter注入
                        method.invoke(beanObj,bean);
                    }
                }
            }

            if(value != null && !"".equals(value)) {
                //拼接方法名
                String methodName = StringUtils.getSetterMethodNameByFieldName(propertyName);
                //获取方法对象
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj,value);
            }
        }
        //存储到map中
        singletonObjects.put(name,beanObj);
        return beanObj;
    }


    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {

        Object bean = getBean(name);
        if(bean != null) {
            return clazz.cast(bean);
        }
        return null;
    }
}
