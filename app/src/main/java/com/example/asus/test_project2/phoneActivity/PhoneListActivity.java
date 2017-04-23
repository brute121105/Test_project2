package com.example.asus.test_project2.phoneActivity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.asus.test_project2.R;
import com.example.asus.test_project2.adapter.PhoneAdapter;
import com.example.asus.test_project2.model.Phone;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneListActivity extends AppCompatActivity {
    List<Phone> phones = null;
    PhoneAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        phones = DataSupport.findAll(Phone.class);
        adapter = new PhoneAdapter(PhoneListActivity.this,R.layout.item,phones);
        ListView lv = (ListView)this.findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

}
