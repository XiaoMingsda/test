/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.dao;

import cn.edu.ldu.pojo.Member;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface MemberDao {
    //返回表中所有记录
    public List<Member> queryMember();
    //根据id查找记录
    public Member queryMemberById(Integer id);
    //保存用户信息
    public int saveMember(Member member);
    //根据ID和密码查看是否有此记录
    public Member queryMemberByIdAndPassword(Integer id, String password);
}
