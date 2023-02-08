package com.carl.ioc.framework.beans;

import lombok.Builder;
import lombok.Data;

/**
 * @program: SpringDemo
 * @description: 封装bean标签下的property
 *  name 属性
 *  ref 属性
 *  value 属性 : 基本数据类型及String类型数据赋值
 * @author: Mr.Carl
 **/
@Data
@Builder
public class Property {
    //bean名称
    private String name;
    //bean路径
    private String ref;
    //基本数据类型的值
    private String value;

    public Property() {
    }

    public Property(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * 设置
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * 获取
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Property{name = " + name + ", ref = " + ref + ", value = " + value + "}";
    }
}
