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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.BannerAdvertisementActivity;
import com.tieuluan.mainprojectmycoffee.activity.ThucDonActivity;
import com.tieuluan.mainprojectmycoffee.model.LoaiMon;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LoaiMon> list;
    Context context;

    private class VH extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tvten;
        ImageView image;

        View.OnClickListener onClickListener;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvten=itemView.findViewById(R.id.textViewItemLoaiMon);
            image=itemView.findViewById(R.id.imageViewItemLoaiMon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }

    public RecAdapter(List<LoaiMon> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recycle_view, parent, false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        final LoaiMon loaimon=list.get(position);
        vh.tvten.setText(loaimon.getTenLoaiMon());
         //Glide.with(context).load(loaimon.getHinhAnh()).into(vh.image);
        Picasso.with(context).load("https:"+loaimon.getHinhAnh()).into(vh.image);
        vh.image.setScaleType(ImageView.ScaleType.FIT_XY);

        vh.onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dùng chuyển dữ liệu intent từ màn hình này sang màn hình kia
                Intent thucdonActivity=new Intent(context.getApplicationContext(), ThucDonActivity.class);
                String maMon=loaimon.getMaLoaiMon()+"";
                thucdonActivity.putExtra("MaMon",maMon);
                context.startActivity(thucdonActivity);
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
