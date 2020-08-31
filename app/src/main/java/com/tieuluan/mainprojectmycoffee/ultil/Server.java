package com.tieuluan.mainprojectmycoffee.ultil;

import com.tieuluan.mainprojectmycoffee.Database.DataSource.CartRepository;
import com.tieuluan.mainprojectmycoffee.Database.Local.CartDatabase;
import com.tieuluan.mainprojectmycoffee.model.ThucDon;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public static String localhost="192.168.0.158";

    public static String getLoaiMon="http://"+localhost+":8080/sever/getLoaiMons.php";
    public static String getTinTuc="http://"+localhost+":8080/sever/getTinTucs.php";
    public static String getTrachNhiemCongDong="http://"+localhost+":8080/sever/getTracNhiemCongDongs.php";
    public static String postGioHangLenDatHang="http://"+localhost+":8080/sever/postDatHang.php";
    public static String postGioHangLenChiTietDatHang="http://"+localhost+":8080/sever/postChiTietDatHang.php";
    public static String postGioHangLenChiTietDatHang1="http://"+localhost+":8080/sever/postChiTietDatHang1.php";
    public static CartDatabase cartDatabase;
    public static CartRepository cartRepository;
}
