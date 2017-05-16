package com.example.asus.test_project2.phoneActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asus.test_project2.R;
import com.example.asus.test_project2.model.Phone;
import com.example.asus.test_project2.util.OkHttpUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneGetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_get);
        Button pullBtn = (Button)this.findViewById(R.id.pull_contact);
        pullBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Map<String,String> params = new HashMap<String,String>();
                params.put("pa","ddd222");
                params.put("pb","222ddfdfad3223");
                String body = OkHttpUtil.okHttpPost("http://10.0.102.30:8080/testPost",params);

                /*List<Phone> phs = new ArrayList<Phone>();
                Phone phone = new Phone();
                phone.setName("b01");
                phone.setPhone("123");
                phs.add(phone);
                DataSupport.saveAll(phs);*/
                System.out.println("批量保存测试");
            }
        });
    }
}
