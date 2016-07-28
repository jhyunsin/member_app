package com.abc.app.memberapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberListActivity extends Activity {
    ListView lv_memberlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        ArrayList<MemberBean> list = this.getList();
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this,list));
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Object o = lv_memberlist.getItemIdAtPosition(i);
                MemberBean member = (MemberBean) o;
                Toast.makeText(MemberListActivity.this,"선택한이름"+member.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<MemberBean> getList(){
        ArrayList<MemberBean> list = new ArrayList<MemberBean>();
            String[] names = {
                    "cupcake",
                    "donut",
                    "eclair",
                    "froyo",
                    "gingerbread",
                    "honeycomb",
                    "icecream",
                    "jellybean",
                    "kitkat",
                    "lollipop"
        };
        int i=0;
        while ( i<names.length ){
            MemberBean member = new MemberBean();
            member.setName(names[i]);
            member.setPhone("010-2088-5756");

           list.add(member);
            i++;
        }



        return  list;
    }

}
