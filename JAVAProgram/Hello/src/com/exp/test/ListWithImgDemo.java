package com.exp.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class ListWithImgDemo extends JFrame implements ActionListener {
    /**
     * @param args
     */
    //文字数组
    String[] data = { "好友1", "好友2", "好友3", "好友4", "好友5", "好友6", "好友7",
            "好友8", "好友9", "好友10", "好友11" , "好友12" , "好友13" , "好友14"
            , "好友15" , "好友16" , "好友17" , "好友18" };
    Icon icon1 = new ImageIcon("src/img/1-1.png");
    Icon icon2 = new ImageIcon("src/img/1-1.png");
    Icon icon3 = new ImageIcon("src/img/1-1.png");
    Icon icon4 = new ImageIcon("src/img/1-1.png");
    Icon icon5 = new ImageIcon("src/img/1-1.png");
    Icon icon6 = new ImageIcon("src/img/1-1.png");
    Icon icon7 = new ImageIcon("src/img/1-1.png");
    Icon icon8 = new ImageIcon("src/img/1-1.png");
    Icon icon9 = new ImageIcon("src/img/1-1.png");
    Icon icon10 = new ImageIcon("src/img/1-1.png");
    Icon icon11 = new ImageIcon("src/img/1-1.png");
    Icon icon12 = new ImageIcon("src/img/1-1.png");
    Icon icon13 = new ImageIcon("src/img/1-1.png");
    Icon icon14 = new ImageIcon("src/img/1-1.png");
    Icon icon15 = new ImageIcon("src/img/1-1.png");
    Icon icon16 = new ImageIcon("src/img/1-1.png");
    Icon icon17 = new ImageIcon("src/img/1-1.png");
    Icon icon18 = new ImageIcon("src/img/1-1.png");
    //图片数组
    Icon[] icons = { icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8,
            icon9, icon10, icon11, icon12, icon13, icon14, icon15, icon16, icon17, icon18 };
    JList list;
    JButton add;
    JButton remove;
    JScrollPane listScroller;
    DefaultListModel listModel;

    ListWithImgDemo() {
        listModel = new DefaultListModel();
        for(int i=0;i<data.length;i++){
            listModel.add(i, data[i]);
        }
        list = new JList(listModel);
        list.setCellRenderer(new MyCellRenderer(icons));//使用自己的CellRenderer
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 设置单一选择模式（每次只能
        listScroller = new JScrollPane(list);
        add = new JButton("Add");
        add.addActionListener(this);
        remove = new JButton("Remove");
        remove.addActionListener(this);
        add(listScroller, BorderLayout.CENTER);//添加带滚动条的list
        add(add,BorderLayout.NORTH);
        add(remove,BorderLayout.SOUTH);
        setSize(200, 500);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        ListWithImgDemo demo = new ListWithImgDemo();

        demo.setVisible(true);
    }

    /**
     * Add与Remove按钮事件
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == add) {
            if (listModel.getSize() < data.length) {
                listModel.add(listModel.getSize(), data[listModel.getSize()]);
            }
        }
        if (e.getSource() == remove) {
            if (listModel.getSize() > 0) {
                listModel.remove(listModel.getSize() - 1);
            }
        }
    }
}
