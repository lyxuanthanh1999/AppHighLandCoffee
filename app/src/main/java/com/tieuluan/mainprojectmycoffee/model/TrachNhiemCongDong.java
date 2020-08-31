package com.tieuluan.mainprojectmycoffee.model;

public class TrachNhiemCongDong {
    private int maTNCD;
    private String TieuDe;
    private String NoiDung;
    private String AnhTinTuc;

    public int getMaTNCD() {
        return maTNCD;
    }

    public void setMaTNCD(int maTNCD) {
        this.maTNCD = maTNCD;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnhTinTuc() {
        return AnhTinTuc;
    }

    public void setAnhTinTuc(String anhTinTuc) {
        AnhTinTuc = anhTinTuc;
    }

    public TrachNhiemCongDong(int maTNCD, String tieuDe, String noiDung, String anhTinTuc) {
        this.maTNCD = maTNCD;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        AnhTinTuc = anhTinTuc;
    }

    public TrachNhiemCongDong() {
    }
}
