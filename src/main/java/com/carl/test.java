package com.carl;

import com.carl.ioc.framework.context.ApplicationContext;
import com.carl.ioc.framework.context.support.ClassPathXmlApplicationContext;
import com.carl.service.UserService;

/**
 * @program: SpringDemo
 * @description:
 * @author: Mr.Carl
 **/
public class test {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add();
    }
}
