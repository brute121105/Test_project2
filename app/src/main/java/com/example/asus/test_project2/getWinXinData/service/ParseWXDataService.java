package com.example.asus.test_project2.getWinXinData.service;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.alibaba.fastjson.JSON;
import com.example.asus.test_project2.getWinXinData.model.PhoneText;
import com.example.asus.test_project2.model.Phone;

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
            Log.d("ParseWXDataService","waiting自动start:"+waitTimes);
            Thread.sleep(waitTimes);
            Log.d("ParseWXDataService","waiting自动stop:"+waitTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("ParseWXDataService","Thread-->InterruptedException");
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

    public void sendDataByHTTP(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder().add("data","ddd").build();
                    Request request = new Request.Builder()
                            .url("http://192.168.1.104:8080/android_web/mainServlet")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String resData = response.body().string();
                    Log.d("setDataByHTTP-->", resData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }).start();
    }
    public String queryData(){
        List<PhoneText> phones = DataSupport.findAll(PhoneText.class);
        /*for(PhoneText ph:phones){
            System.out.println("---"+ph.getPhoneText());
        }*/
        System.out.println("size--"+phones.size());
        return JSON.toJSONString(phones);

    }
public void send(){
    new Thread(new Runnable(){
        @Override
        public void run() {
            URL url = null;
            try {
                url = new URL("http://192.168.1.104:8080/android_web/mainServlet");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection)url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.setConnectTimeout(50000);
// 设置允许输出
            conn.setDoOutput(true);
            try {
                conn.setRequestMethod("POST");
                // 设置User-Agent: Fiddler
               // conn.setRequestProperty("ser-Agent", "Fiddler");
                // 设置contentType
                //conn.setRequestProperty("Content-Type","application/json");
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes("data="+queryData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();
}
    public void httpPost(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String url="http://192.168.1.105:8080/android_web/mainServlet";
                HttpPost httppost=new HttpPost(url);
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                String data = queryData();
                System.out.println("data--"+data);
                params.add(new BasicNameValuePair("data",data));
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
                    HttpClient client = new DefaultHttpClient();
                    // 请求超时
                    client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
                    // 读取超时
                    client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
                    HttpResponse response = client.execute(httppost);
                    if(response.getStatusLine().getStatusCode()==200) {
                        String result = EntityUtils.toString(response.getEntity());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
