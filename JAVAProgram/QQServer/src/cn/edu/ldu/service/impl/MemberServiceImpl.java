/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.service.impl;

import cn.edu.ldu.dao.MemberDao;
import cn.edu.ldu.dao.impl.MemberDaoImpl;
import cn.edu.ldu.pojo.Member;
import cn.edu.ldu.service.MemberService;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MemberServiceImpl implements MemberService{
    private MemberDao memberDao = new MemberDaoImpl();
    @Override
    public void registMember(Member member) {
        memberDao.saveMember(member);
    }

    @Override
    public Member MemberLogin(Member member) {
        //根据id和密码进行登录
        return memberDao.queryMemberByIdAndPassword(member.getId(), member.getPassword());
    }

    @Override
    public boolean existsId(Integer id) {
        if (memberDao.queryMemberById(id) == null){
            return false;
        }
        return true;
    }

    @Override
    public List<Member> queryMember() {
        return memberDao.queryMember();
    }
    
    
    
}
