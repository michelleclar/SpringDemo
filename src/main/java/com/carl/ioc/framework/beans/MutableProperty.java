package com.carl.ioc.framework.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: SpringDemo
 * @description: 用来存储和管理property对象
 * @author: Mr.Carl
 **/
public class MutableProperty implements Iterable<Property> {

    /**
     * Carl
     * TODO:定义list集合对象,用来描述property对象
     */
    private final List<Property> propertyValueList;

    public MutableProperty() {
        this.propertyValueList = new ArrayList<Property>();
    }

    public MutableProperty(List<Property> propertyValueList) {
        if (propertyValueList == null) {
            this.propertyValueList = new ArrayList<Property>();
        } else {
            this.propertyValueList = propertyValueList;
        }
    }

    /**
     * Carl
     * TODO:获取所有的PropertyValue对象,返回数组的形式
     * param:
     * return:
     */
    public Property[] getPropertyValues() {
        return this.propertyValueList.toArray(new Property[0]);
    }

    /**
     * Carl
     * TODO:根据name属性值获取PropertyValue对象
     * param:
     * return:
     */
    public Property getPropertyValue(String propertyName) {
        for (Property pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }

    /**
     * Carl
     * TODO:判断集合是否为空
     * param:
     * return:
     */
    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }

    /**
     * Carl
     * TODO: 添加PropertyValue对象
     * param:
     * return:
     */
    public MutableProperty addPropertyValue(Property pv) {
        //判断集合中存储的PropertyValue对象是否和传递进行重复,如果重复了,进行覆盖
        for (int i = 0; i < this.propertyValueList.size(); i++) {
            Property currentPv = this.propertyValueList.get(i);
            //覆盖
            if (currentPv.getName().equals(pv.getName())) {
                this.propertyValueList.set(i, new Property(pv.getName(), pv.getRef(), pv.getValue()));
                return this;
            }
        }
        this.propertyValueList.add(pv);
        return this;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * 获取迭代器对象
     *
     * @return an Iterator.
     */
    public Iterator<Property> iterator() {
        return propertyValueList.iterator();
    }

    /**
     * Carl
     * TODO:是否包含bean
     */
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }
}
