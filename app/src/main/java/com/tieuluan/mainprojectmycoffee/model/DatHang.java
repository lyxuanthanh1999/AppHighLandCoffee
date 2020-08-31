package com.tieuluan.mainprojectmycoffee.model;

public class DatHang {
    private long OrtherId;
    private int OrtherStatus;
    private String OrtherDetail;
    private String OrtherCommnet;
    private String OrtherAddress;
    private String UserName;
    public DatHang()
    {}

    public long getOrtherId() {
        return OrtherId;
    }

    public void setOrtherId(long ortherId) {
        OrtherId = ortherId;
    }

    public int getOrtherStatus() {
        return OrtherStatus;
    }

    public void setOrtherStatus(int ortherStatus) {
        OrtherStatus = ortherStatus;
    }

    public String getOrtherDetail() {
        return OrtherDetail;
    }

    public void setOrtherDetail(String ortherDetail) {
        OrtherDetail = ortherDetail;
    }

    public String getOrtherCommnet() {
        return OrtherCommnet;
    }

    public void setOrtherCommnet(String ortherCommnet) {
        OrtherCommnet = ortherCommnet;
    }

    public String getOrtherAddress() {
        return OrtherAddress;
    }

    public void setOrtherAddress(String ortherAddress) {
        OrtherAddress = ortherAddress;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

}
