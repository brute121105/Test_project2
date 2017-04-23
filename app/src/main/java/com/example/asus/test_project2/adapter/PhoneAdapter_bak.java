package com.example.asus.test_project2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.test_project2.R;
import com.example.asus.test_project2.model.Phone;

import java.util.List;

/**
 * Created by asus on 2017/4/22.
 */

public class PhoneAdapter_bak extends RecyclerView.Adapter<PhoneAdapter_bak.ViewHolder>{
    private List<Phone> mPhome;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.name);
            tv2 = (TextView)itemView.findViewById(R.id.phone);
        }
    }

    public PhoneAdapter_bak(List<Phone> phome) {
        mPhome = phome;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Phone ph = mPhome.get(position);
        holder.tv1.setText(ph.getName());
        holder.tv2.setText(ph.getPhone());
    }
    @Override
    public int getItemCount() {
        return mPhome.size();
    }
}
