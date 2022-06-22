/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.dao.impl;

import cn.edu.ldu.dao.BaseDao;
import cn.edu.ldu.dao.MemberDao;
import cn.edu.ldu.pojo.Member;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MemberDaoImpl extends BaseDao implements MemberDao{
    String sql = "";
    @Override
    public List<Member> queryMember() {
        sql = "select ID, NAME, PASSWORD, EMAIL, HEADIMAGE from member";
        return queryForList(Member.class, sql);
    }

    @Override
    public Member queryMemberById(Integer id) {
        String sql = "select ID, NAME, PASSWORD, EMAIL, HEADIMAGE from member where ID = ?";
        return queryForOne(Member.class, sql, id);
    }

    @Override
    public int saveMember(Member member) {
        String sql = "insert into member(ID, NAME, PASSWORD, EMAIL, HEADIMAGE) values(?,?,?,?,?)";
        return update(sql, member.getId(), member.getName(), member.getPassword(), member.getEmail(), member.getHeadImage());
    }

    @Override
    public Member queryMemberByIdAndPassword(Integer id, String password) {
        String sql = "select ID, NAME, PASSWORD, EMAIL, HEADIMAGE from member where ID = ? and PASSWORD = ?";
        return queryForOne(Member.class, sql, id, password);
    }
    
}
