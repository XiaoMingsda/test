package com.hspedu.string_;

public class String01 {
    public static void main(String[] args) {
        //1.String 对象用于保存字符串，也就是一组字符序列
        //2. "Jack"字符串常量，双引号括起来的字符序列
        //3. 字符串的字符使用Unicode字符编码，一个字符(不区分字母还是汉字)占两个字节
        //4. String 类有很多构造器，构造器的重载
        //   常用的有 String s1 = new String();
        //   String s2 = new String(String original);
        //   String s3 = new String(char[] a);
        //   String s4 = new String(char[] a,int startIndex,int count)
        //   String s5 = new String(byte[] b)
        //5. String 类实现了接口 Serializable [String 可以串行化：可以在网络传输]\
        //                接口 Comparable [String 对象可以比较大小]
        //6. String 是final 类(但不为static类)，不能被其他的类继承
        //7. String 有属性  private final char value[]; 用于存放字符串内容
        //8. 一定要注意：value 是一个final类型，不可以修改：即value不能指向
        //   新的地址，但是单个字符内容是可以变化的
            /*
            final char[] value = {'a','b','c'};
            value[0] = 'H';//单个字符内容是可以变化的
            char[] v2 = {'t','o','m'};
            //value = v2; 不可以修改 value地址
            */
        String name = "Jack";
        String name2 = new String("Jack");
        System.out.println(name.hashCode()+ " " + name2.hashCode());
        System.out.println(name2.intern());
        System.out.println(name2);
        //name = name.replace('c','b');
        //System.out.println(name + "----------");
        System.out.println(name.hashCode() + " " + "Jack".hashCode());
        name = "Hello";
        System.out.println(name.getBytes() + " " + "Jack".getBytes());
        P p = new P(2);
        P p1 = new P(3);
        System.out.println(p.getN1());
        p = p1;
        System.out.println(p.getN1());
    }
}

class P {
    private final int n1;

    public P(int x) {
        n1 = x;
    }

    public int getN1() {
        return n1;
    }
}