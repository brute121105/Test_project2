package com.example.asus.test_project2;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.test_project2.model.Phone;
import com.example.asus.test_project2.phoneActivity.PhoneListActivity;
import com.example.asus.test_project2.service.ContactService;
import com.example.asus.test_project2.util.GetPermissionUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button startBtn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetPermissionUtil.getReadAndWriteContactPermision(MainActivity.this,MainActivity.this);
        Log.d("MainActivity","1759 con---");

        Button createDataBase = (Button)findViewById(R.id.create_database);
        Button addData = (Button)findViewById(R.id.add_data);
        Button queryData = (Button)findViewById(R.id.query_data);
        Button ListData = (Button)findViewById(R.id.list_data);
        Button addContact = (Button)findViewById(R.id.add_contacts);
        createDataBase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("MainActivity","create database start");
                Connector.getDatabase();
                Log.d("MainActivity","create database stop");
            }
        });
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Phone phone = new Phone();
                phone.setName("a1002");
                phone.setPhone("12346678911");
                phone.setPhoneText("人找车，电话1244646564");
                phone.save();
            }
        });
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                List<Phone> phones = DataSupport.findAll(Phone.class);
                for(Phone ph:phones){
                    Log.d("MainActivity",ph.getId()+"　"+ph.getPhone()+"  "+ph.getPhoneText());
                }

            }
        });
        ListData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhoneListActivity.class);
                startActivity(intent);
            }
        });
        final ContactService contactService = new ContactService();
        addContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("MainActivity","8888add contacts");
                //testAddContact("张三","11111111111");
                contactService.testAddContact(MainActivity.this.getContentResolver(),"李四","32658");

            }
        });

        startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("activity","99980");
                try {
                    //打开系统设置中辅助功能
                    Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "找到抢红包，然后开启服务即可", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
