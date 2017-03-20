package com.yushilei.myapp.entitys;

import com.google.gson.annotations.SerializedName;

/**
 * @auther by yushilei.
 * @time 2017/3/20-17:05
 * @desc
 */

public class Update {
    @SerializedName("ret")
    private int ret;// 接口状态描述
    @SerializedName("msg")
    private String msg;// 接口状态描述
    @SerializedName("isHasNewVersion")
    private boolean isHasNewVersion;//是否 有新版本 notNUll
    @SerializedName("newVersionCode")
    private String newVersionCode;//新版本 版本号
    @SerializedName("apkName")
    private String apkName;// apk名称

    @SerializedName("appSize")
    private String appSize;// APK 大小 eg:2.3m
    @SerializedName("appLength")
    private long appLength;// APK 大小 字节单位

    @SerializedName("isMustUpdate")
    private boolean isMustUpdate;//是否 强制升级
    @SerializedName("downUrl")
    private String downUrl;//apk下载地址
    @SerializedName("appDesc")
    private String appDesc;//版本描述信息

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isHasNewVersion() {
        return isHasNewVersion;
    }

    public void setHasNewVersion(boolean hasNewVersion) {
        isHasNewVersion = hasNewVersion;
    }

    public String getNewVersionCode() {
        return newVersionCode;
    }

    public void setNewVersionCode(String newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public long getAppLength() {
        return appLength;
    }

    public void setAppLength(long appLength) {
        this.appLength = appLength;
    }

    public boolean isMustUpdate() {
        return isMustUpdate;
    }

    public void setMustUpdate(boolean mustUpdate) {
        isMustUpdate = mustUpdate;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    @Override
    public String toString() {
        return "Update{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", isHasNewVersion=" + isHasNewVersion +
                ", newVersionCode='" + newVersionCode + '\'' +
                ", apkName='" + apkName + '\'' +
                ", appSize='" + appSize + '\'' +
                ", appLength=" + appLength +
                ", isMustUpdate=" + isMustUpdate +
                ", downUrl='" + downUrl + '\'' +
                ", appDesc='" + appDesc + '\'' +
                '}';
    }
}
