package com.hspedu.stringbuffer_;

public class StringBufferMethod {
    public static void main(String[] args) {
        //append
        StringBuffer stringBuffer = new StringBuffer("韩顺平");
        stringBuffer.append("教育");
        System.out.println(stringBuffer);

        //删
        //删除2~4的字符，不包括4
        stringBuffer.delete(2,4);
        System.out.println(stringBuffer);

        //改
        //将0~1的字符替换为 魏
        stringBuffer.replace(0,1,"魏");
        System.out.println(stringBuffer);

        //查找指定的子串在字符串第一次出现的索引，如果找不到返回-1
        int indexOf = stringBuffer.indexOf("育");
        System.out.println(indexOf);

        //插
        //在索引为2的位置插入"平教"，原来索引为2的内容自动后移
        stringBuffer.insert(2,"平教");
        System.out.println(stringBuffer);

        //长度
        System.out.println(stringBuffer.length());
    }
}
