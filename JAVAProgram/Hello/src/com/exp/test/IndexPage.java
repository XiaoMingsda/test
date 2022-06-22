package com.exp.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class IndexPage {
    /*
     * author:命运的信徒 date:2019/1/19 arm:熟悉swing布局
     */
    static JFrame jf = new JFrame("矿井管理系统");
    private static void firtPage() {
        // 1.设置窗体大小和标题

        jf.setPreferredSize(new Dimension(700, 700));
        // 2.设置关闭窗口就是关闭程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 最精准的布局模式空布局
        jf.setLayout(null);
        // 设置定位
        JLabel jl = new JLabel("矿井平硐位移监测系统", JLabel.CENTER);
        jl.setPreferredSize(new Dimension(680, 30));
        jf.add(jl);
        jl.setBounds(0, 0, 690, 30);
        jl.setFont(new Font("宋体", Font.BOLD, 24));
        jl.setForeground(Color.decode("#375a7f"));
        // 菜单栏
        // 新建一个菜单条
        JMenuBar jb = new JMenuBar();
        jf.add(jb);
        jb.setBounds(0, 40, 690, 30);
        jb.setBackground(Color.decode("#65991a"));
        // 新建一个菜单选项
        JMenu jmenu = new JMenu("首页");
        jmenu.setPreferredSize(new Dimension(100, 30));
        jmenu.setForeground(Color.white);
        jb.add(jmenu);
        // 新建一个菜单项
        JMenu jmenu0 = new JMenu("平硐位移");
        jmenu0.setPreferredSize(new Dimension(100, 30));
        jmenu0.setForeground(Color.white);
        jmenu.setPreferredSize(new Dimension(100, 30));
        jmenu.setForeground(Color.white);
        jb.add(jmenu0);
        // 新建一个菜单项
        JMenuItem jm = new JMenuItem("卷扬室");
        JMenuItem jmi = new JMenuItem("配电室");
        JMenuItem jmi0 = new JMenuItem("平硐");
        JMenuItem jmi1 = new JMenuItem("-240米");
        JMenuItem jmi2 = new JMenuItem("-290米以上水平");
        JMenuItem jmi3 = new JMenuItem("-290米水仓");
        jmenu0.add(jm);
        jmenu0.add(jmi);
        jmenu0.add(jmi0);
        jmenu0.add(jmi1);
        jmenu0.add(jmi2);
        jmenu0.add(jmi3);

        // 新建一个菜单选项
        JMenu jmenu1 = new JMenu("更多");
        jmenu1.setForeground(Color.white);
        jmenu1.setPreferredSize(new Dimension(100, 30));
        jb.add(jmenu1);
        // 新建一个菜单项
        JMenuItem jm0 = new JMenuItem("退出程序");
        JMenuItem jm1 = new JMenuItem("技术教程");
        jmenu1.add(jm0);
        jmenu1.add(jm1);
        // 以下是显示位移的地方
        // 放置图片
        JLabel jl3 = new JLabel(new ImageIcon("WebRoot/img/hd.PNG"));
        jf.add(jl3);
        jl3.setBounds(0, 80, 700, 600);
        //开始监听事件
        jm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //销毁当前页面
                closeThis();
                //打开一个新的页面
                new FirstPage().firtPage();
            }
        });
        // 3.设置窗体可见
        jf.pack();
        jf.setVisible(true);
    }
    public static void closeThis(){
        jf.dispose();
    }
    public static void main(String[] args) {
        firtPage();
    }
}
