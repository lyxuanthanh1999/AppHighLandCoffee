package com.tieuluan.mainprojectmycoffee.model;

public class LoaiMon {
    private int MaLoaiMon;
    private String TenLoaiMon;
    private String MoTa;
    private String HinhAnh;

    public LoaiMon() {
    }

    public LoaiMon(int maLoaiMon, String tenLoaiMon, String moTa, String hinhAnh) {
        MaLoaiMon = maLoaiMon;
        TenLoaiMon = tenLoaiMon;
        MoTa = moTa;
        HinhAnh = hinhAnh;
    }

    public int getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        MaLoaiMon = maLoaiMon;
    }

    public String getTenLoaiMon() {
        return TenLoaiMon;
    }

    public void setTenLoaiMon(String tenLoaiMon) {
        TenLoaiMon = tenLoaiMon;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
