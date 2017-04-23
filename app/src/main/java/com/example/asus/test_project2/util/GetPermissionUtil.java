package com.example.asus.test_project2.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.asus.test_project2.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/4/23.
 */

public class GetPermissionUtil {
    public static void getReadAndWriteContactPermision(Context context, Activity activity){
        List<String> permissionList = new ArrayList<String>();
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_CONTACTS);
        }
        if (ContextCompat.checkSelfPermission(context,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_CONTACTS);
        }
        if(!permissionList.isEmpty()){
            String[] permisions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(activity,permisions,1);
        }

    }
}
