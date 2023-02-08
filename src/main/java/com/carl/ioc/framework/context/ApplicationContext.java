package com.carl.ioc.framework.context;

import com.carl.ioc.framework.beans.factory.BeanFactory;

/**
 * @program: SpringDemo
 * @description: 非延时加载功能
 * @author: Mr.Carl
 **/
public interface ApplicationContext extends BeanFactory {
    //进行配置文件加载并进行对象创建
    void refresh() throws Exception;
}
