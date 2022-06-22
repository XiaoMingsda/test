package com.hspedu.string_;

public class StringMethod02 {
    public static void main(String[] args) {
        //1. toUpperCase转换成大写
        String s = "heLLo";
        System.out.println(s.toUpperCase());//HELLO
        //2. toLowerCase
        System.out.println(s.toLowerCase());//hello
        //3. concat拼接字符串
        String s1 = "宝玉";
        s1 = s1.concat("林黛玉").concat("薛宝钗").concat("together");
        System.out.println(s1);
        //4.replace 替换字符串中的字符
        s1 = "宝玉 and 林黛玉 林黛玉 林黛玉";
        //在s1中，将所有的 林黛玉 替换成薛宝钗
        s1 = s1.replace("林黛玉","薛宝钗");
        System.out.println(s1);
        //5. split 分割字符串
        String address = "E:\\aaa\\bbb";
        String[] split = address.split("\\\\");
        for (String ss: split) {
            System.out.println(ss);
        }
        //6. toCharArray 转换成字符数组
        s = "happy";
        char[] chs = s.toCharArray();
        for (char ch: chs) {
            System.out.println(ch);
        }
        //7. compareTo 比较两个字符串的大小，如果前者大，
        //   则返回正数，后者大，则返回负数，如果相等，返回0
        /*
        * public int compareTo(String anotherString) {
        int len1 = value.length;
        int len2 = anotherString.value.length;
        int lim = Math.min(len1, len2);
        char v1[] = value;
        char v2[] = anotherString.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
        * */
        String a = "jackack";
        String b = "jack";
        System.out.println(a.compareTo(b));
        //8. format 格式字符串 static方法
        String name = "jack";
        int age = 18;
        double score = 99.6;
        char sex = '男';
        //1. %s,%d,%.2f,%c 称为占位符
        //2. 这些占位符由后面变量来替换
        //3. %s 表示后面由 字符串来替换
        //4. %d 是整数来替换
        //5. %.2f 表示使用小数来替换，替换后，只会保留小数点两位，并且进行四舍五入的处理
        //6. %c 使用char 类型来替换
        String formatStr = "我的姓名是%s 年龄是%d 成绩是%.2f 性别是%c." +
                            "希望大家喜欢我!";
        String info2 = String.format(formatStr,name,age,score,sex);
        System.out.println(info2);
    }
}
