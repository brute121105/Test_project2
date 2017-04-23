package com.example.asus.test_project2.phoneActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        List<Phone> phones = DataSupport.findAll(Phone.class);
        PhoneAdapter adapter = new PhoneAdapter(PhoneListActivity.this,R.layout.item,phones);
        ListView lv = (ListView)this.findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }
}
