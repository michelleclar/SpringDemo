package com.carl.ioc.framework.beans.support;

/**
 * Carl
 * TODO:
 * 用来解析配置文件
 * return:
 */
public interface BeanDefinitionReader {

    //获取注册表对象
    BeanDefinitionRegistry getRegistry();
    //加载配置文件并在注册表中进行注册
    void loadBeanDefinitions(String configLocation) throws Exception;
}
