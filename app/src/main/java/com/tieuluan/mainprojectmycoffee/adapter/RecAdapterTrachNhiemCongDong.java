package com.tieuluan.mainprojectmycoffee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.TrachNhiemCongDongActivity;
import com.tieuluan.mainprojectmycoffee.model.TrachNhiemCongDong;

import java.util.ArrayList;

public class RecAdapterTrachNhiemCongDong extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<TrachNhiemCongDong> list;
    private Context context;

    public RecAdapterTrachNhiemCongDong(ArrayList<TrachNhiemCongDong> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tvTen;
        ImageView img;
        //Sự kiện click của recycleview
        View.OnClickListener onClickListener;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.textviewTNCD);
            img = itemView.findViewById(R.id.imageViewTNCD);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view_trach_nhiem_cong_dong,parent, false);
        return new RecAdapterTrachNhiemCongDong.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RecAdapterTrachNhiemCongDong.VH vh=(RecAdapterTrachNhiemCongDong.VH)holder;
        final TrachNhiemCongDong trachNhiemCongDong = list.get(position);
        vh.tvTen.setText(trachNhiemCongDong.getTieuDe());
        Picasso.with(context).load(trachNhiemCongDong.getAnhTinTuc()).into(vh.img);

        vh.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TNCD = new Intent(context,TrachNhiemCongDongActivity.class);
                String maTNCD = String.valueOf(list.get(position).getMaTNCD());
                TNCD.putExtra("MaTNCD",maTNCD);
                context.startActivity(TNCD);

                Toast.makeText(context, "Item TrachNhiemCongDong", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
