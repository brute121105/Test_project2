package com.example.asus.test_project2.getWinXinData;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.test_project2.R;

public class GetWeinxinDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_weinxin_data);
        Button bt1 = (Button)findViewById(R.id.start_getWeixinData_service);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                Toast.makeText(GetWeinxinDataActivity.this, "开启服务，然后进入微信朋友圈滚动一下", Toast.LENGTH_LONG).show();
                Log.d("GetWeinxinDataActivity","ttt");
            }
        });
    }
}
