package com.abc.app.memberapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**  create read up dele
 * Created by hb2009 on 2016-07-27.
 */
public class MemberDAO extends SQLiteOpenHelper{
    public static final String TABLE_NAME ="member";
    public static final String ID ="id";
    public static final String PW ="pw";
    public static final String NAME ="name";
    public static final String SSN ="ssn";
    public static final String EMALE ="email";
    public static final String PHONE ="phone"; ///외부에서 받아들이는 녀석들

    public MemberDAO(Context context) {

        super(context, "hanbitdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//SQL db 만드는 oncreate
        String sql = "create table if not exists "
        +TABLE_NAME
        +" ( "
        +ID+" text primary key,"
        +PW+" text, "
        +NAME+" text, "
        +SSN+" text, "
        +EMALE+" text, "
        +PHONE+" text "
        +" ); ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "+TABLE_NAME;
        db.execSQL(sql);
        this.onCreate(db);
    }

////////////////////////////////////////////////////////////////

    public int insert(MemberBean mem){//회원가입
        int result = 0;
        String sql = "insert into member(id,pw,name,reg_date,ssn,email,profile_img,phone)"+"values(?,?,?,?,?,?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }

    public int delete(MemberBean mem){//탈퇴
        int result = 0;
        String sql = "delete from member where id = ? and pw=?";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
       return result;
    }

    public int update(MemberBean mem) {
        int result =0;
        String sql = "update member set pw= ? , email = ? where id = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        return result;
    }


    //list
    public List<MemberBean> list() {
        String sql ="select * from member";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return list;
    }



    //findbyPK
    public MemberBean findById(String pk) {
        String sql = "select * from member where id=?";
        MemberBean temp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return temp;
    }

    // findbynNotPK
    public List<MemberBean> findByname(String name) {
        String sql = "select * from member where name=?";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);


        return list;
    }




    //count
    public int count() {
        // 임플에서 return dao.count(); 만들고 에러잡기형식
        String sql = "select count(*) as count from member";
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return count;
    }

    public boolean login(MemberBean param) {
        // TODO Auto-generated method stub
        boolean loginOk = false;
        String sql = "";
        if (param.getId()!=null
                && param.getPw()!=null
                && this.existId(param.getId())) {
            MemberBean member = this.findById(param.getId());
            if (member.getPw().equals(param.getPw())) {//멤버에서 넘어온 비번과 지금 비번이 같다면...
                loginOk = true;
            }
        }
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return loginOk;
    }

    public boolean existId(String id){
        boolean existOK = false;
        int result = 0;
        String sql = "select count(*) as count from member where id = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return existOK;
    }

    public int findId(String id) {
        // 아이디찾기 for 성별수
        int result = 0;
        String sql = "select count(*) count from member where id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);


        return result;
    }

    public int genderCount(String gender) {
        // 성별 회원수
        int	result = 0;
        String sql = "select count(*) count form member where";
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(sql);
        return result;
    }


}
