package com.example.asus.test_project2;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.asus.test_project2.getWinXinData.service.ParseWXDataService;

import java.util.List;

public class EnvelopeService extends AccessibilityService {
    ParseWXDataService service = new ParseWXDataService();
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.d("EnvelopeService","----"+eventType+"----");
        AccessibilityNodeInfo nodeInfo =  this.getRootInActiveWindow();
        if(nodeInfo==null) return;
        service.getText(nodeInfo);
    }

    @Override
    public void onInterrupt() {

    }

}
