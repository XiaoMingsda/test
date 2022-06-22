/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.web;

import cn.edu.ldu.pojo.Member;
import cn.edu.ldu.service.MemberService;
import cn.edu.ldu.service.impl.MemberServiceImpl;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class MemberWeb {
    private MemberService memberService = new MemberServiceImpl();
    
    public boolean registerMember(Member member) {
        if (memberService.existsId(member.getId())) {
            //JOptionPane.showMessageDialog(null, "Id已存在", "错误提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        memberService.registMember(member);
        return true;
    }
    
    public boolean MemberLogin(Member member) {
        Member result = memberService.MemberLogin(member);
        if (result == null) {
            //JOptionPane.showMessageDialog(null, "Id或密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void displayAllRows() {
        List<Member> memberList = memberService.queryMember();
        for (Member member : memberList) {
            System.out.println(member);
        }
    }
    
}
