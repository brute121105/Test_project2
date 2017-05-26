package com.example.asus.test_project2.phoneActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asus.test_project2.R;
import com.example.asus.test_project2.service.ContactService;
import com.example.asus.test_project2.util.LogUtil;

public class ImportContactActivity extends AppCompatActivity {
    EditText editText;
    ContactService contactService = new ContactService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_contact);
        Button button = (Button)findViewById(R.id.import_contact);
        editText = (EditText)findViewById(R.id.phone_numbers);
        button.setOnClickListener(btnLst);
    }

    View.OnClickListener btnLst = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phones = editText.getText().toString();
            LogUtil.d("ImportContactActivity",phones);
            if(phones.contains("\n")){
                String[] phoneStr = phones.split("\n");
                for(String str:phoneStr){
                    if(str.matches(".*?\\s[\\d]{11}")){
                        String[] phone= str.split("\\s");
                        LogUtil.d("ImportContactActivity导入",phone[0]+":"+phone[1]);
                        contactService.testAddContact(ImportContactActivity.this.getContentResolver(),phone[0],phone[1]);
                    }
                }
            }
        }
    };
}
