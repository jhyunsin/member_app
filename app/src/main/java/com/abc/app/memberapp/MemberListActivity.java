package com.abc.app.memberapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberListActivity extends Activity {
    ListView lv_memberlist;
    MemberService service;
    Phone phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        service = new MemberServiceImpl(this.getApplicationContext());
        phone = new Phone(this,this);

        ArrayList<MemberBean> list = service.list();
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        lv_memberlist.setAdapter(new MemberAdapter(this,list));
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Object o = lv_memberlist.getItemAtPosition(i);
                MemberBean member = (MemberBean) o;
                Toast.makeText(MemberListActivity.this,"선택한이름"+member.getName(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MemberListActivity.this,DetailActivity.class);
                intent.putExtra("id",member.getId());
                startActivity(intent);

            }
        });
        lv_memberlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                new AlertDialog.Builder(MemberListActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete >>> service.delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


                return true;
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
