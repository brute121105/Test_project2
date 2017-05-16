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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.asus.test_project2.MainActivity;
import com.example.asus.test_project2.R;
import com.example.asus.test_project2.getWinXinData.GetWeinxinDataActivity;
import com.example.asus.test_project2.model.Phone;
import com.example.asus.test_project2.phoneActivity.PhoneListActivity;
import com.example.asus.test_project2.service.ContactService;
import com.example.asus.test_project2.util.LogUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/4/22.
 */

public class PhoneAdapter extends ArrayAdapter<Phone>{
    private int resourceId;
    private  List<Phone> phones;
    private PhoneListActivity phoneListActivity;
    ContactService contactService = new ContactService();
    TextView tv;
    int countAll;

    public PhoneAdapter(Context context, int resource, List<Phone> objects,PhoneListActivity phoneListActivity,TextView tv,int countAll) {
        super(context, resource, objects);
        resourceId = resource;
        phones = objects;
        this.phoneListActivity = phoneListActivity;
        this.tv = tv;
        this.countAll = countAll;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(this.getContext()).inflate(resourceId,parent,false);
        TextView tv1 = (TextView)view.findViewById(R.id.name);
        TextView tv2 = (TextView)view.findViewById(R.id.phone);
        final Button btn = (Button)view.findViewById(R.id.add_button);
        final Phone ph = this.getItem(position);
        tv1.setText(ph.getName());
        tv2.setText(ph.getPhone());
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LogUtil.d("添加号码", JSON.toJSONString(ph));
                contactService.testAddContact(phoneListActivity.getContentResolver(),ph.getName(),ph.getPhone());
                Phone rmPhone = phones.remove(position);
                rmPhone.setIs_add_contact(1);
                rmPhone.update(rmPhone.getId());
                PhoneAdapter.this.notifyDataSetChanged();
                tv.setText("剩余/总数："+ phones.size()+"/"+countAll);

            }
        });
        return view;
    }
}
