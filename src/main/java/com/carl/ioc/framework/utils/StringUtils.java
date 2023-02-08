package com.carl.ioc.framework.utils;

/**
 * @program: SpringDemo
 * @description:
 * @author: Mr.Carl
 **/
public class StringUtils {
    private StringUtils(){

    }

    public static String getSetterMethodNameByFieldName(String fieldName){
        //UserDao -- > setUserDao
        String methodName = "set"+ fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return methodName;
    }
}
