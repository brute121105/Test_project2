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
    int count = 0;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.d("EnvelopeService","----"+eventType+"----"+count);
        count=count+1;
        AccessibilityNodeInfo nodeInfo =  this.getRootInActiveWindow();
        AccessibilityNodeInfo nodeInfo2 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cp7").get(0);
        service.getText(nodeInfo);
        //getText(nodeInfo,"aaa");
        if(nodeInfo2!=null){
            nodeInfo2.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
        }
       /* Log.d("EnvelopeService","----"+eventType);
        switch (eventType){
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                Log.d("EnvelopeService","TYPE_VIEW_CLICKEDTYPE_VIEW_CLICKED");
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                Log.d("EnvelopeService","TYPE_VIEW_FOCUSEDTYPE_VIEW_FOCUSED");
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                Log.d("EnvelopeService","TYPE_WINDOW_STATE_CHANGED");
                AccessibilityNodeInfo nodeInfo =  this.getRootInActiveWindow();
                nodeInfo2 = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cp7").get(0);
                getText(nodeInfo,"aaa");
                nodeInfo2.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                Log.d("EnvelopeService","TYPE_VIEW_SCROLLEDTYPE_VIEW_SCROLLED");
                AccessibilityNodeInfo scNodeInfo =  this.getRootInActiveWindow();
                AccessibilityNodeInfo nodeInfo1 = scNodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cp7").get(0);
                //getText(scNodeInfo,"bbb");
                nodeInfo1.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                Log.d("EnvelopeService","TYPE_WINDOWS_CHANGED");
                AccessibilityNodeInfo scNodeInfo2 =  this.getRootInActiveWindow();
                AccessibilityNodeInfo nodeInfo3 = scNodeInfo2.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/cp7").get(0);
                nodeInfo3.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                break;
        }*/

    }
    public AccessibilityNodeInfo findNodeByClassName(AccessibilityNodeInfo pNode){
        AccessibilityNodeInfo node = null;
        if(pNode!=null){
            if("android.widget.ListView".equals(pNode.getClassName())){
                node = pNode;
            }else{
                findNodeByClassName(pNode.getChild(0));
            }
        }
        return node;

    }
  public void getText(AccessibilityNodeInfo nodeInfo,String flag){

       if(nodeInfo!=null&&nodeInfo.getChildCount()>0){
            List<AccessibilityNodeInfo> items = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/lr");
            if(items!=null&&items.size()>0){
                for(AccessibilityNodeInfo item:items){
                    String text = item.getText().toString();
                    Log.d("EnvelopeService",flag+"TYPE_VIEW_SCROLLEDTYPE_VIEW_SCROLLED555666-->"+text);
                }
            }
        }

        int waitTimes = 3000+(int) (Math.random()*1200);
        try {
            Log.d("EnvelopeService","waiting自动start:"+waitTimes);
            Thread.sleep(waitTimes);
            Log.d("EnvelopeService","waiting自动stop:"+waitTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("EnvelopeService","Thread-->InterruptedException");
        }
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        Log.d("key event","into key");
        int ev = event.getAction();
        switch (ev){
            case KeyEvent.ACTION_DOWN:
                Log.d("key event","into ACTION_DOWNACTION_DOWN");
            case KeyEvent.ACTION_UP:
                Log.d("key event","into ACTION_UPACTION_UP");
            case KeyEvent.KEYCODE_ENTER:
                Log.d("key event","into KEYCODE_ENTERKEYCODE_ENTER");
        }
        return super.onKeyEvent(event);

    }

    @Override
    public void onInterrupt() {

    }

}
