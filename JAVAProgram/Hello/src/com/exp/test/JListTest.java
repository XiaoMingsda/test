package com.exp.test;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JListTest extends JFrame {

    JPopupMenu popupMenu =null;
    JList list=null;
    JPanel mainPanel=null;
    JScrollPane scrollPane=null;

    public JListTest() {
        initComponent();
        setSize(200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent() {
        mainPanel = new JPanel();
        scrollPane = new JScrollPane();
        popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Open")); //添加菜单项Open
        popupMenu.add(new JMenuItem("Save"));
        mainPanel.setLayout(new BorderLayout());
        list = new JList();
        list.setModel(new DefaultListModel());

        list.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
//    maybeShowPopup(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                list.setSelectedIndex(list.locationToIndex(e.getPoint())); //获取鼠标点击的项
                maybeShowPopup(e);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }
            //弹出菜单
            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()&&list.getSelectedIndex()!=-1) {

                    //获取选择项的值
                    Object selected = list.getModel().getElementAt(list.getSelectedIndex());
                    System.out.println(selected);
                    popupMenu.show(e.getComponent(),e.getX(), e.getY());
                }
            }



        });

        list.setListData(new Object[] { "name", "age", "sex", "name", "age",
                "name", "age", "name", "age" });
        scrollPane.setViewportView(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(mainPanel);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new JListTest();

    }

}













