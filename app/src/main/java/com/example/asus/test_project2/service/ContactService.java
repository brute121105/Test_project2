package com.example.asus.test_project2.service;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Created by asus on 2017/4/23.
 */

public class ContactService {
    //添加联系人，使用事务
    public void testAddContact(ContentResolver resolver,String name, String phone) {

        //首先插入空值，再得到rawContactsId ，用于下面插值
        ContentValues values = new ContentValues();
        //insert a null value
        Uri rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactsId = ContentUris.parseId(rawContactUri);

        //往刚才的空记录中插入姓名
        values.clear();
        //A reference to the _ID that this data belongs to
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, rawContactsId);
        //"CONTENT_ITEM_TYPE" MIME type used when storing this in data table
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        //The name that should be used to display the contact.
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
        //insert the real values
        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
        //插入电话
        values.clear();
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, rawContactsId);
        //String "Data.MIMETYPE":The MIME type of the item represented by this row
        //String "CONTENT_ITEM_TYPE": MIME type used when storing this in data table.
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        resolver.insert(ContactsContract.Data.CONTENT_URI, values);

    }
}
