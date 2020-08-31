package com.tieuluan.mainprojectmycoffee.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.TinTucActivity;
import com.tieuluan.mainprojectmycoffee.model.ChiTietDatHang;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;

import java.util.List;

public class RecAdapterLichSuMuaHang extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ChiTietDatHang> list ;
    Context context;

    public RecAdapterLichSuMuaHang(List<ChiTietDatHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    private class VH extends RecyclerView.ViewHolder
    {
        TextView tenuserlsmh,
                trangthailsmh,
                diachilsmh,
                binhluanlsmh,
                tenmonlsmh,
                soluongmonlsmh,
                giamonlsmh;

        View.OnClickListener onClickListener;

        public VH(@NonNull View itemView) {
            super(itemView);
            tenuserlsmh=itemView.findViewById(R.id.tenuserlsmh);
            trangthailsmh=itemView.findViewById(R.id.trangthailsmh);
            diachilsmh=itemView.findViewById(R.id.diachilsmh);
            binhluanlsmh=itemView.findViewById(R.id.binhluanlsmh);
            tenmonlsmh=itemView.findViewById(R.id.tenmonlsmh);
            soluongmonlsmh=itemView.findViewById(R.id.soluongmonlsmh);
            giamonlsmh=itemView.findViewById(R.id.giamonlsmh);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recycle_view_lich_su_mua_hang, parent, false);

        return new RecAdapterLichSuMuaHang.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecAdapterLichSuMuaHang.VH vh=(RecAdapterLichSuMuaHang.VH)holder;
        final ChiTietDatHang chiTietDatHang=list.get(position);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        vh.tenuserlsmh.setText(firebaseUser.getEmail());

//        vh.trangthailsmh.setText(chiTietDatHang.getOrderStatus());
        if(chiTietDatHang.getOrderStatus()==0)
        {
            vh.trangthailsmh.setText("Chưa Giao Hàng");
        }
        else
        {
            vh.trangthailsmh.setText("Đã Giao Hàng");
        }
        vh.diachilsmh.setText(chiTietDatHang.getOrderAddress());

        vh.binhluanlsmh.setText(chiTietDatHang.getOrderComment());

        vh.tenmonlsmh.setText(chiTietDatHang.getTenMon());

//        vh.soluongmonlsmh.setText(chiTietDatHang.getSoLuong());
        vh.soluongmonlsmh.setText(String.valueOf(chiTietDatHang.getSoLuong()));
 //       vh.giamonlsmh.setText(String.valueOf(chiTietDatHang.getGia()));
        vh.giamonlsmh.setText(String.valueOf(chiTietDatHang.getGia()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
