package com.carl.ioc.framework.beans;

import lombok.Builder;
import lombok.Data;

/**
 * @program: SpringDemo
 * @description: 用来封装bean标签数据
 *       id 属性
 *       class 属性
 *       property 子标签的数据
 * @author: Mr.Carl
 **/
@Builder
public class BeanDefinition {
    private String id;
    private String className;
    private MutableProperty propertyValues;

    public BeanDefinition() {
        propertyValues = new MutableProperty();
    }


    public BeanDefinition(String id, String className, MutableProperty propertyValues) {
        this.id = id;
        this.className = className;
        this.propertyValues = propertyValues;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取
     * @return propertyValues
     */
    public MutableProperty getPropertyValues() {
        return propertyValues;
    }

    /**
     * 设置
     * @param propertyValues
     */
    public void setPropertyValues(MutableProperty propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String toString() {
        return "BeanDefinition{id = " + id + ", className = " + className + ", propertyValues = " + propertyValues + "}";
    }
}
