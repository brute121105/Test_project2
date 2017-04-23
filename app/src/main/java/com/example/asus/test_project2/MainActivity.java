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
        Button createDataBase = (Button)findViewById(R.id.create_database);
        Button addData = (Button)findViewById(R.id.add_data);
        Button queryData = (Button)findViewById(R.id.query_data);
        Button ListData = (Button)findViewById(R.id.list_data);
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


      /*  List<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_CONTACTS)!=PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_CONTACTS);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_CONTACTS);
        }
        if(!permissionList.isEmpty()){
            String[] permisions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permisions,1);
        }
        testAddContact();*/

      /*  startBtn = (Button) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("activity","clickkdsddssdo");
                try {
                    //打开系统设置中辅助功能
                    Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "找到抢红包，然后开启服务即可", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //添加联系人，使用事务
    public void testAddContact() {

        String name[] = {"周杰伦", "谢霆锋", "言承旭", "林俊杰", "潘玮柏"};
        for (String ss : name) {
            //首先插入空值，再得到rawContactsId ，用于下面插值
            ContentValues values = new ContentValues();
            //insert a null value
            Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactsId = ContentUris.parseId(rawContactUri);

            //往刚才的空记录中插入姓名
            values.clear();
            //A reference to the _ID that this data belongs to
            values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, rawContactsId);
            //"CONTENT_ITEM_TYPE" MIME type used when storing this in data table
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            //The name that should be used to display the contact.
            values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, ss);
            //insert the real values
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
            //插入电话
            values.clear();
            values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, rawContactsId);
            //String "Data.MIMETYPE":The MIME type of the item represented by this row
            //String "CONTENT_ITEM_TYPE": MIME type used when storing this in data table.
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "13800138000");
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
