package com.example.asus.test_project2.getWinXinData.model;

import org.litepal.crud.DataSupport;

/**
 * Created by asus on 2017/4/22.
 */

public class PhoneText extends DataSupport{
    private String  guid;
    private String phoneText;
    private String fromWxId;
    private String createTime;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }

    public String getFromWxId() {
        return fromWxId;
    }

    public void setFromWxId(String fromWxId) {
        this.fromWxId = fromWxId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
