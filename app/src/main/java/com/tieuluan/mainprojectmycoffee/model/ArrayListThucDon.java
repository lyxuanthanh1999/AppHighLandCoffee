package com.tieuluan.mainprojectmycoffee.model;

import java.util.ArrayList;
import java.util.List;

public class ArrayListThucDon {
    public ArrayList<ThucDon> list;
    public ArrayListThucDon()
    {
        list=new ArrayList<>();
    }

    public void addThucDon(ThucDon thucDon)
    {
        list.add(thucDon);
    }

    public ArrayListThucDon(ArrayList<ThucDon> list) {
        this.list = list;
    }
    public List<ThucDon> getList()
    {
        return list;
    }

    public int getSize()
    {
        return list.size();
    }

}
