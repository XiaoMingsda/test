package com.exp.exp10;

import javax.swing.*;

public class test {

    public static void main(String[] args) {
        DefaultListModel defaultListModel = new DefaultListModel();
        defaultListModel.add(0, 2000);
        defaultListModel.add(1, 3000);
        defaultListModel.add(2, 3000);
        defaultListModel.add(3, 8000);
        defaultListModel.add(4, 3000);
        for (int i = 0; i < defaultListModel.getSize(); i++) {
            System.out.println(defaultListModel.get(i));
        }
        defaultListModel.remove(4);
        for (int i = 0; i < defaultListModel.getSize(); i++) {
            System.out.println(defaultListModel.get(i));
        }
    }

}


class User {
    private Integer id;
    private String username;

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
