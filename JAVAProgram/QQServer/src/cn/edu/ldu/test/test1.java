/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.test;

import cn.edu.ldu.dao.MemberDao;
import cn.edu.ldu.dao.impl.MemberDaoImpl;
import cn.edu.ldu.pojo.Member;
import cn.edu.ldu.security.Cryptography;
import cn.edu.ldu.web.MemberWeb;
import java.security.CryptoPrimitive;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class test1 {
    public static void main(String[] args) {
//        MemberDao memberDao = new MemberDaoImpl();
//        List<Member> list = memberDao.queryMember();
//        for (Member member : list) {
//            System.out.println(member);
////        }
//        Member member = memberDao.queryMemberById(10000);
//        System.out.println(member);
        MemberWeb memberWeb = new MemberWeb();
        Member member = new Member();
        member.setId(3000);
        member.setName("好好学习");
        member.setPassword("123456");
        member.setEmail("hhxx@sina.com");
        member.setHeadImage("i9003.jpg");
        memberWeb.registerMember(member);
        
        
        
        //密码加密
        Member member1 = new Member();
        member1.setId(70000);
        member1.setName("天天向上");
        String encryptPassword = Cryptography.getHash("123456", "sha-256");
        member1.setPassword(encryptPassword);
        member1.setEmail("ttxs@sina.com");
        member1.setHeadImage("i9004.jpg");
        
        memberWeb.registerMember(member1);
        
        memberWeb.displayAllRows();
    }
}
