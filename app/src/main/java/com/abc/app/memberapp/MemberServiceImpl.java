package com.abc.app.memberapp;

import android.content.Context;

import java.util.List;

/**
 * Created by hb2009 on 2016-07-27.
 */
public class MemberServiceImpl implements MemberService{

    MemberDAO dao;
    MemberBean session;

    public MemberServiceImpl(Context context) {
        dao = new MemberDAO(context);
    }

    @Override
    public String regist(MemberBean bean) {
        // 1등록
        String msg = "";
        MemberBean temp = this.findById(bean.getId());
        if (temp==null) {
            System.out.println(bean.getId()+ "가 존재하지 않음,가입가능한 ID");
            int result = dao.insert(bean);
            if (result == 1) {
                msg = "success";
            } else {
                msg = "fail";
            }
        }else{
            System.out.println(bean.getId()+ "가 존재함 ,가입 불가한 ID");
            msg = "fail";
        }
        return msg;
    }

    @Override
    public MemberBean show() {
        // 2보기
        return session;
    }

    @Override
    public void update(MemberBean mem) {
        // 3수정
        int result = dao.update(mem);
        if (result ==1) {
            session = this.findById(mem.getId());
            System.out.println("업데이트결과 성공");
        }else{
            System.out.println("업데이트결과 실패");
        }


    }

    @Override
    public void delete(MemberBean mem) {
        // 4삭제
        String result = "";
        if (dao.delete(mem) ==1) {
            result = "삭제성공";
            session = null;
        } else {
            result = "삭제실패";
        }

    }

    @Override
    public int count() {
        // 컨트롤러에서 int count = service.count(); 만들고 서비스 / 임플 까지 타고 타고옴
        return dao.count();//토스
    }

    @Override
    public MemberBean findById(String findID) {
        // TODO Auto-generated method stub
//		MemberBean t =
//		MemberBean t2 = new MemberBean(t.getId(),t.getPw(),t.getName(),t.getSsn());
        return dao.findById(findID);
    }

    @Override
    public List<MemberBean> list() {
        // TODO Auto-generated method stub
        return dao.list();
    }

    @Override
    public List<MemberBean> findByName(String findName) {
        // TODO Auto-generated method stub
        return dao.findByname(findName);
    }



    @Override
    public int genderCount(String gender) {
        // TODO Auto-generated method stub
        return dao.genderCount(gender);
    }

    @Override
    public void logout(MemberBean member) {

        if (member.getId().equals(session.getId()) &&
                member.getPw().equals(session.getPw())) {
            session = null;
        }

    }

    @Override
    public List<?> findBy(String keyword) {
        // TODO Auto-generated method stub
        return dao.findByname(keyword);
    }


}
