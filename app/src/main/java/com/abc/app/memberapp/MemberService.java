package com.abc.app.memberapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hb2009 on 2016-07-27.
 */
public interface MemberService {

    public void regist(MemberBean bean);

    public MemberBean show();

    public void update(MemberBean mem);

    public void delete(MemberBean mem);

    public int count();

    public MemberBean findById(String findId);

    public ArrayList<MemberBean> list();

    public List<MemberBean> findByName(String findName);

    public int genderCount(String gender);

    public void logout(MemberBean member);
//    public ArrayList<MemberBean> findBy(String keyword);

    public boolean login(MemberBean member);


}
