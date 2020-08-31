package com.tieuluan.mainprojectmycoffee.model;

import java.util.ArrayList;

public class ArrrayListLoaiMon {
    public ArrayList<LoaiMon> list;

    public ArrayList<LoaiMon> getList() {
        return list;
    }

    public void setList(ArrayList<LoaiMon> list) {
        this.list = list;
    }

    public ArrrayListLoaiMon() {
        list = new ArrayList<>();
    }

    public void addLoaiMon(LoaiMon loaiMon)
    {
        list.add(loaiMon);
    }

    public int getSize(ArrayList<LoaiMon> list)
    {
        return list.size();
    }

}
