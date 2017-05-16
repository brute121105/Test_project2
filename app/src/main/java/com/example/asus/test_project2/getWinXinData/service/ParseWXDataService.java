package com.example.asus.test_project2.getWinXinData.service;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.alibaba.fastjson.JSON;
import com.example.asus.test_project2.getWinXinData.model.PhoneText;
import com.example.asus.test_project2.model.Phone;
import com.example.asus.test_project2.util.FileUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.litepal.crud.DataSupport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by asus on 2017/5/1.
 */

public class ParseWXDataService {
    public void getText(AccessibilityNodeInfo nodeInfo){
        Set<String> set = new HashSet<String>();
        if(nodeInfo!=null&&nodeInfo.getChildCount()>0){
            List<AccessibilityNodeInfo> items = new ArrayList<AccessibilityNodeInfo>();
            List<AccessibilityNodeInfo> items0 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/lr");
            List<AccessibilityNodeInfo> items1 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/b4e");

            items.addAll(items0);
            items.addAll(items1);
            if(items!=null&&items.size()>0){
                for(AccessibilityNodeInfo item:items){
                    String text = item.getText().toString();
                    set.add(text);
                    Log.d("data----->","TYPE_VIEW_SCROLLEDTYPE_VIEW_SCROLLED555666-->"+text);
                }
            }
        }
        save(set);
        int waitTimes = 4000+(int) (Math.random()*1200);
        try {
            Thread.sleep(waitTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        AccessibilityNodeInfo nodeInfo2 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cp7").get(0);
        if(nodeInfo2!=null){
            nodeInfo2.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }
    }
    public void save(Set<String> set){
        if(set.size()==0) return;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        for(String str:set){
            PhoneText pt = new PhoneText();
            UUID uuid = UUID.randomUUID();
            pt.setGuid(uuid.toString());
            pt.setCreateTime(currentTime);
            pt.setPhoneText(str);
            boolean bl = pt.save();
            Log.d("save--","保存-》"+bl);
        }
    }

    public String queryData(){
        List<PhoneText> phones = DataSupport.findAll(PhoneText.class);
        /*for(PhoneText ph:phones){
            System.out.println("---"+ph.getPhoneText());
        }*/
        System.out.println("size--"+phones.size());
        String jsonData = JSON.toJSONString(phones);
        FileUtil.writeContent2File("/sdcard/A_my_Wxdata/","bt0215.txt",jsonData);
        FileUtil.uploadMultiFile("http://10.0.102.172:8080/upload","/sdcard/A_my_Wxdata/","bt0215.txt");
        return jsonData;

    }


}
