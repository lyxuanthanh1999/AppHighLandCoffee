package com.tieuluan.mainprojectmycoffee.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.w3c.dom.Text;

import java.util.List;

public class RecAdapterCart extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Cart> list;
    Context context;
    public class VH extends RecyclerView.ViewHolder
    {
        ImageView imageviewitemcart;
        TextView textviewnameitemcart;
        TextView textviewsugariceitemcart;
        TextView textviewpriceitemcart;
        ElegantNumberButton countitemcart;

        public RelativeLayout view_background;
        public LinearLayout view_forebackground;



        public VH(@NonNull View itemView) {
            super(itemView);
            imageviewitemcart = itemView.findViewById(R.id.imagecart);
            textviewnameitemcart = itemView.findViewById(R.id.namecart);
            textviewsugariceitemcart = itemView.findViewById(R.id.sugaricecart);
            textviewpriceitemcart = itemView.findViewById(R.id.pricecart);
            countitemcart = itemView.findViewById(R.id.countcart);
            view_background= itemView.findViewById(R.id.view_background);
            view_forebackground=itemView.findViewById(R.id.view_forebackground);
        }

    }
    public RecAdapterCart(List<Cart> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_recycle_view_cart, parent, false);

        return new RecAdapterCart.VH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final VH vh=(VH)holder;
        final Cart cart=list.get(position);
        Picasso.with(context).load(cart.HinhAnh).into(vh.imageviewitemcart);
        vh.textviewnameitemcart.setText(cart.name);
        vh.textviewpriceitemcart.setText(String.valueOf(cart.price));
        vh.countitemcart.setNumber(String.valueOf(cart.amount));
        vh.textviewsugariceitemcart
                .setText(new StringBuilder(" Đường :"+cart.duong+"%")
                        .append("\n")
                        .append(" Đá : "+cart.da+"%"));
        //luôn cập nhập mỗi khi user thay đổi Số Lượng
        final Double Gia1Ly = cart.price / cart.amount;
        vh.countitemcart.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Cart cart = list.get(position);
                cart.amount = newValue;
                cart.price =Double.parseDouble(String.valueOf(newValue*Gia1Ly));
                Server.cartRepository.updateToCart(cart);
                Toast.makeText(context, "GIÁ MỚI CẬP NHẬP : "+cart.price, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Đã Cập Nhập Thành Công Khi Thay Đổi Giỏ Hàng", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void removeItem(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void restoreItem(Cart cart,int position)
    {
        list.add(position,cart);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
