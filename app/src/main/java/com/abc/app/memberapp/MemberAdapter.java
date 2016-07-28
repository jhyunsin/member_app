package com.abc.app.memberapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hb2009 on 2016-07-28.
 */
public class MemberAdapter extends BaseAdapter{

    ArrayList<MemberBean> list;
    LayoutInflater inflater;
    int[] imgs = {
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop

    };
    public MemberAdapter(Context context, ArrayList<MemberBean> list) {//다형성 X
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup g) {
        ViewHoler holder;
        if (v==null) {

            v = inflater.inflate(R.layout.member_list, null);
            holder = new ViewHoler();
            holder.ivPhoto = (ImageView) v.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) v.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
        }else{
            holder = (ViewHoler) v.getTag();
        }
        Log.d("사진인덱스",list.get(i).getName());
        holder.ivPhoto.setImageResource(imgs[i]);
        holder.tvName.setText(list.get(i).getName());
        holder.tvPhone.setText((list.get(i).getPhone()));
        return v;
    }
    //inner class
    static class  ViewHoler{/// 컴포넌트에 들어가는 3가지 객체
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPhone;/// 프라이빗이 없는 이유는 나 혼자 쓰니까
                        /// 게터세터 쓸 시간도 없다 빠르게 더 빠르게
    }
}
