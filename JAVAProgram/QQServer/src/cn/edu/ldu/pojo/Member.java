/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.pojo;

import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author Administrator
 */
public class Member {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private String headImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", headImage=" + headImage + '}';
    }
    
}
