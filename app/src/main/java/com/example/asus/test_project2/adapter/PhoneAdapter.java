package com.example.asus.test_project2.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.test_project2.R;
import com.example.asus.test_project2.model.Phone;
import com.example.asus.test_project2.phoneActivity.PhoneListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/4/22.
 */

public class PhoneAdapter extends ArrayAdapter<Phone>{
    private int resourceId;
    private  List<Phone> phones;

    public PhoneAdapter(Context context, int resource, List<Phone> objects) {
        super(context, resource, objects);
        resourceId = resource;
        phones = objects;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Phone ph = this.getItem(position);
        View view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
        TextView tv1 = (TextView)view.findViewById(R.id.name);
        TextView tv2 = (TextView)view.findViewById(R.id.phone);
        Button btn = (Button)view.findViewById(R.id.add_button);
        tv1.setText(ph.getName());
        tv2.setText(ph.getPhone());
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("PhoneAdapter","369--");
                Log.d("PhoneAdapter",phones.get(position).getPhone());
                phones.remove(position);
                PhoneAdapter.this.notifyDataSetChanged();

            }
        });
        return view;
    }
}
