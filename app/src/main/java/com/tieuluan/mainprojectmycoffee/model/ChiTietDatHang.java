package com.tieuluan.mainprojectmycoffee.model;

public class ChiTietDatHang {
    private int orderStatus;
    private String orderComment;
    private String orderAddress;
    private String tenMon;
    private int soLuong;
    private double gia;

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public ChiTietDatHang() {
    }

    public ChiTietDatHang(int orderStatus, String orderComment, String orderAddress, int soLuong, double gia) {
        this.orderStatus = orderStatus;
        this.orderComment = orderComment;
        this.orderAddress = orderAddress;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
}
