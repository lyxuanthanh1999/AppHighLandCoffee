package com.tieuluan.mainprojectmycoffee.model;

import java.util.Date;

public class TinTuc {
    private int MaTin;
    private String TieuDe;
    private String NoiDung;
    private String AnhTinTuc;
    private Date ngayDang;

    public int getMaTin() {
        return MaTin;
    }

    public void setMaTin(int maTin) {
        MaTin = maTin;
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

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public TinTuc() {
    }

    public TinTuc(int maTin, String tieuDe, String noiDung, String anhTinTuc, Date ngayDang) {
        MaTin = maTin;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        AnhTinTuc = anhTinTuc;
        this.ngayDang = ngayDang;
    }
}
