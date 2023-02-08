package com.carl.ioc.framework.beans.xml;

import com.carl.ioc.framework.beans.BeanDefinition;
import com.carl.ioc.framework.beans.MutableProperty;
import com.carl.ioc.framework.beans.Property;
import com.carl.ioc.framework.beans.support.BeanDefinitionReader;
import com.carl.ioc.framework.beans.support.BeanDefinitionRegistry;
import com.carl.ioc.framework.beans.support.impl.SimpleBeanDefinitionRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @program: SpringDemo
 * @description: 针对xml配置文件进行解析的类
 * @author: Mr.Carl
 **/
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    //声明注册表对象
    private BeanDefinitionRegistry registry;
    public XmlBeanDefinitionReader(){
        registry = new SimpleBeanDefinitionRegistry();
    }
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }
    //加载bean
    public void loadBeanDefinitions(String configLocation) throws Exception {
        //获取类路径下的对象
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation);
        //使用dom4j进行xml配置文件的解析
        SAXReader reader = new SAXReader();
        //根据Document对象获取根标签对象(beans)
        Document document = reader.read(is);
        //获取根标签下所有的bean标签对象
        Element rootElement = document.getRootElement();
        //解析bean标签
        parseBean(rootElement);
    }

    private void parseBean(Element rootElement) {

        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            //获取id
            String id = element.attributeValue("id");
            //获取class属性
            String className = element.attributeValue("class");

            //将id属性和class属性封装BeanDefinition对象中
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setId(id);
            beanDefinition.setClassName(className);

            //获取bean标签下所有的property标签对象
            List<Element> list = element.elements("property");
            MutableProperty mutablePropertyValues = new MutableProperty();
            for (Element element1 : list) {
                String name = element1.attributeValue("name");
                String ref = element1.attributeValue("ref");
                String value = element1.attributeValue("value");
                //封装
                Property propertyValue = new Property(name,ref,value);
                mutablePropertyValues.addPropertyValue(propertyValue);
            }

            //将mutablePropertyValues封装到BeanDefinition对象中
            beanDefinition.setPropertyValues(mutablePropertyValues);

            //将BeanDefinition注册到注册表中
            registry.registerBeanDefinition(id,beanDefinition);
        }
    }
}
