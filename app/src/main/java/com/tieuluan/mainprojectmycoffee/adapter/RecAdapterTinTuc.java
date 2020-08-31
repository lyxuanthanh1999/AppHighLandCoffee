package com.tieuluan.mainprojectmycoffee.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.ThucDonActivity;
import com.tieuluan.mainprojectmycoffee.activity.TinTucActivity;
import com.tieuluan.mainprojectmycoffee.model.LoaiMon;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;

import java.util.ArrayList;
import java.util.List;

public class RecAdapterTinTuc extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TinTuc> list;
    Context context;

    private class VH extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tvten;
        ImageView image;
        View.OnClickListener onClickListener;
        public VH(@NonNull View itemView) {
            super(itemView);
            tvten=itemView.findViewById(R.id.textviewTinTuc);
            image=itemView.findViewById(R.id.imageViewTinTuc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }

    public RecAdapterTinTuc(List<TinTuc> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recycle_view_tin_tuc, parent, false);

        return new RecAdapterTinTuc.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecAdapterTinTuc.VH vh=(RecAdapterTinTuc.VH)holder;
        final TinTuc tinTuc=list.get(position);
        vh.tvten.setText(tinTuc.getTieuDe());

        Glide.with(context).load(tinTuc.getAnhTinTuc()).into(vh.image);

        vh.image.setScaleType(ImageView.ScaleType.FIT_XY);

        // sự kiện recycleview click
        vh.onClickListener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tinTucActivity=new Intent(context.getApplicationContext(), TinTucActivity.class);
                String maTinTuc =String.valueOf(tinTuc.getMaTin());
                tinTucActivity.putExtra("MaTinTuc",maTinTuc);
                context.startActivity(tinTucActivity);
                //Toast.makeText(context, tinTuc.getMaTin()+" Tên : "+tinTuc.getTieuDe(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
