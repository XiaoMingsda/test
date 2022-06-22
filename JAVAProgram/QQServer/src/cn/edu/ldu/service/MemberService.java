/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.ldu.service;

import cn.edu.ldu.pojo.Member;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface MemberService {
    public void registMember(Member member);
    public Member MemberLogin(Member member);
    public boolean existsId(Integer id);
    public List<Member> queryMember();
}
