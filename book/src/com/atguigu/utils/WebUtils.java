package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {


    //使用泛型省去强转
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            System.out.println("注入之前：" + bean);
            //核心代码，需要传入JavaBean对象和map对象
            BeanUtils.populate(bean, map);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    /**
     * 将字符串转化称为int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    //parse 解析
    public static int parseInt(String strInt, int defaultValue) {
        if (strInt == null || strInt == "") {
            return defaultValue;
        }
        try {
            //转成功
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //转失败
        return defaultValue;
    }

}
