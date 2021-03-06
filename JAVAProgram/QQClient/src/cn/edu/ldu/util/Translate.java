/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.util;

import java.io.*;

/**
 *
 * @author Administrator
 */
public class Translate {

    /*
    * 对象转化为字节数组形式，实现对象序列化
    * */
    public static byte[] ObjectToByte(Object obj) {
        byte[] buffer = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();//字节数组输出流
            ObjectOutputStream oo = new ObjectOutputStream(bo);//对象输出流
            oo.writeObject(obj);//输出对象
            buffer = bo.toByteArray();//对象序列化
        } catch (IOException ex) {}
        return buffer;
    }

    /*
    * 字节数组转化为Object对象形式，实现对象反序列化
    * */
    public static Object ByteToObject(byte[] buffer) {
        Object obj = null;
        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(buffer);//字节数组输入流
            ObjectInputStream oi = new ObjectInputStream(bi);//对象输入流
            obj = oi.readObject();//转为对象
        } catch (IOException | ClassNotFoundException ex) {}
        return obj;
    }

}
