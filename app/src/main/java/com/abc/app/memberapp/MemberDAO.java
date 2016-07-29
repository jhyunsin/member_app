package com.abc.app.memberapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public static final String PROFILE ="profile"; ///외부에서 받아들이는 녀석들

    public MemberDAO(Context context) {

        super(context, "hanbitdb", null, 1);
        Log.d("DB 생성체트","========여기까지 진입=======");
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//SQL db 만드는 oncreate
        String sql = "create table if not exists "
        +TABLE_NAME
        +" ( "
        +ID+" text primary key, "
        +PW+" text, "
        +NAME+" text, "
        +SSN+" text, "
        +EMALE+" text, "
        +PHONE+" text, " +
         PROFILE+" text );";

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
        String sql = "insert into "+TABLE_NAME
                +String.format("(%s,%s,%s,%s,%s,%s,%s) ",ID,PW,NAME,SSN,EMALE,PROFILE,PHONE)
                +String.format(" values('%s','%s','%s','%s','%s','%s','%s');"
                ,mem.getId()
                ,mem.getPw()
                ,mem.getName()
                ,mem.getSsn()
                ,mem.getEmail()
                ,mem.getProfile()
                ,mem.getPhone()
        );
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
        String sql ="select "
                +String.format("%s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMALE,PROFILE,PHONE)
                +" from "+TABLE_NAME+";";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor !=null){
            Log.d("DAO LIST","목록조회 성공 !!");
            cursor.moveToFirst();
        }
        do
        {
            MemberBean temp = new MemberBean();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setSsn(cursor.getString(3));
            temp.setEmail(cursor.getString(4));
            temp.setProfile(cursor.getString(5));
            temp.setPhone(cursor.getString(6));
            list.add(temp);
        }while (cursor.moveToNext());
        return list;
    }



    //findbyPK
    public MemberBean findById(String pk) {
        String sql = "select "
                +String.format("%s,%s,%s,%s,%s,%s,%s ",ID,PW,NAME,SSN,EMALE,PROFILE,PHONE)
                +String.format("from "+TABLE_NAME+" where id= '%s' ;",pk);
        MemberBean temp = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor !=null){
            Log.d("DAO FIND_BY_ID ","ID 조회 성공 !!");
            if (cursor.moveToFirst()){
                temp = new MemberBean();
                temp.setId(cursor.getString(0));
                temp.setPw(cursor.getString(1));
                temp.setName(cursor.getString(2));
                temp.setSsn(cursor.getString(3));
                temp.setEmail(cursor.getString(4));
                temp.setProfile(cursor.getString(5));
                temp.setPhone(cursor.getString(6));
            }
        }
            return temp;
    }

    // findbynNotPK
    public List<MemberBean> findByname(String name) {
        String sql = "select * from member where name=?";
        List<MemberBean> list = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);



        return list;
    }




    //count
    public int count() {
        // 임플에서 return dao.count(); 만들고 에러잡기형식
        String sql = "select count(*) as count from member";
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return count;
    }

    public boolean login(MemberBean param) {
        // TODO Auto-generated method stub
        boolean loginOk = false;
        String sql = "select "+PW+" from "+TABLE_NAME+String.format(" where id = '%s';",param.getId()) ;
        SQLiteDatabase db = this.getReadableDatabase();
        String pw = "";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            pw = cursor.getString(0);
        }
        if (pw.equals("")){
            Log.d("DAO 로르인 결과  :", "일치하는 ID가 없음");
            loginOk = false;
        }else {
            Log.d("DAO ID :",param.getId());
            Log.d("DAO PW :",pw);
             loginOk = (pw.equals(param.getPw()))?true :false ;
        }

      System.out.print("LOGIN_OK ?"+loginOk);

        return loginOk;
    }

    public boolean existId(String id){
        boolean existOK = false;
        int result = 0;
        String sql = "select count(*) as count from member where id = ? ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return existOK;
    }

    public int findId(String id) {
        // 아이디찾기 for 성별수
        int result = 0;
        String sql = "select count(*) count from member where id = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);


        return result;
    }

    public int genderCount(String gender) {
        // 성별 회원수
        int	result = 0;
        String sql = "select count(*) count form member where";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return result;
    }


}
