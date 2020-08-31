package com.tieuluan.mainprojectmycoffee.model;

public class ThucDon {
    private int MaMon;
    private String TenMon;
    private int GiaMon;
    private int MaLoaiMon;
    private String MoTa;
    private String HinhAnh;

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getGiaMon() {
        return GiaMon;
    }

    public void setGiaMon(int giaMon) {
        GiaMon = giaMon;
    }

    public int getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(int maLoaiMon) {
        MaLoaiMon = maLoaiMon;
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

    public ThucDon(int maMon, String tenMon, int giaMon, int maLoaiMon, String moTa, String hinhAnh) {
        MaMon = maMon;
        TenMon = tenMon;
        GiaMon = giaMon;
        MaLoaiMon = maLoaiMon;
        MoTa = moTa;
        HinhAnh = hinhAnh;
    }

    public ThucDon() {
    }
}
