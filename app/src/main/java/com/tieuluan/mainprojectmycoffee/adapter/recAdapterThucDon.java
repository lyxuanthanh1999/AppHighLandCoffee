package com.tieuluan.mainprojectmycoffee.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.CpuUsageInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.Database.DataSource.CartRepository;
import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.ThucDonActivity;
import com.tieuluan.mainprojectmycoffee.model.ThucDon;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import java.util.ArrayList;
import java.util.List;

public class recAdapterThucDon extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ThucDon> list;
    public static int sizeOfCup = -1;
    public static int Duong = -1;
    public static int Da = -1;
    public recAdapterThucDon(Context context, List<ThucDon> list) {
        this.context = context;
        this.list = list;
    }

    private class VH extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView tvTen;
        TextView tvGia;
        Button btnAddToCart;
        public VH(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewItemThucDon);
            tvTen=itemView.findViewById(R.id.textViewItemTenThucDon);
            tvGia=itemView.findViewById(R.id.textViewItemGiaThucDon);
            btnAddToCart=itemView.findViewById(R.id.btn_Add_Cart);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view_thucdon,parent,false);
        return new recAdapterThucDon.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            VH vh = (VH)holder;
            final ThucDon thucDon=list.get(position);
            vh.tvTen.setText(thucDon.getTenMon());
            vh.tvGia.setText(thucDon.getGiaMon()+"");
           Picasso.with(context).load(thucDon.getHinhAnh()).into(vh.imageView);
           vh.btnAddToCart.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   openDialogCart(position);
               }
           });
    }

    private void openDialogCart(final int position)
    {
        ImageView image_cart_product;
        TextView textviewTenThucDonCart;
        EditText edt_commentcart;
        final ElegantNumberButton txt_count;
        RadioButton rdbtn_sizeS,rdbtn_sizeM,rdbtn_sizeL
                ,rdbtn_duong100,rdbtn_duong70,rdbtn_duong50,rdbtn_duong30,rdbtn_duong0,
                rdbtn_da100,rdbtn_da70,rdbtn_da50,rdbtn_da30,rdbtn_da0;

        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        View itemView= LayoutInflater.from(context).inflate(R.layout.add_to_cart_layout,null);

        txt_count=itemView.findViewById(R.id.txt_count);

        image_cart_product = itemView.findViewById(R.id.image_cart_product);
        textviewTenThucDonCart=itemView.findViewById(R.id.textviewTenThucDonCart);

        rdbtn_sizeS = itemView.findViewById(R.id.rdbtn_sizeS);
        rdbtn_sizeM = itemView.findViewById(R.id.rdbtn_sizeM);
        rdbtn_sizeL = itemView.findViewById(R.id.rdbtn_sizeL);
//================        Sự Kiện Của Radio Button Để Fán dữ liệu cho Size của ly        ================
        rdbtn_sizeS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    sizeOfCup = 0;
            }
        });
        rdbtn_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    sizeOfCup=1;
            }
        });
        rdbtn_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    sizeOfCup=2;
            }
        });
//=======================================================================================================


        rdbtn_duong100 = itemView.findViewById(R.id.rdbtn_duong100);
        rdbtn_duong70 = itemView.findViewById(R.id.rdbtn_duong70);
        rdbtn_duong50 = itemView.findViewById(R.id.rdbtn_duong50);
        rdbtn_duong30 = itemView.findViewById(R.id.rdbtn_duong30);
        rdbtn_duong0 = itemView.findViewById(R.id.rdbtn_duong0);
//================        Sự Kiện Của Radio Button Để Fán dữ liệu cho Size của ly        ================
        rdbtn_duong100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Duong = 100;
            }
        });
        rdbtn_duong70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Duong = 70;
            }
        });
        rdbtn_duong50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Duong = 50;
            }
        });
        rdbtn_duong30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Duong = 30;
            }
        });
        rdbtn_duong0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Duong = 0;
            }
        });
//=======================================================================================================
        rdbtn_da100 = itemView.findViewById(R.id.rdbtn_da100);
        rdbtn_da70 = itemView.findViewById(R.id.rdbtn_da70);
        rdbtn_da50 = itemView.findViewById(R.id.rdbtn_da50);
        rdbtn_da30 = itemView.findViewById(R.id.rdbtn_da30);
        rdbtn_da0 = itemView.findViewById(R.id.rdbtn_da0);
//================        Sự Kiện Của Radio Button Để Fán dữ liệu cho Size của ly        ================
        rdbtn_da100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Da = 100;
            }
        });
        rdbtn_da70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Da = 70;
            }
        });
        rdbtn_da50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Da = 50;
            }
        });
        rdbtn_da30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Da = 30;
            }
        });
        rdbtn_da0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    Da = 0;
            }
        });
//=======================================================================================================
        Picasso.with(context).load(list.get(position).getHinhAnh()).into(image_cart_product);
        textviewTenThucDonCart.setText(list.get(position).getTenMon());
        builder.setView(itemView);



        builder.setNegativeButton("Thêm Vào Giỏ Hàng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int soLuong = Integer.parseInt(txt_count.getNumber());
                if(soLuong==0)
                {
                    Toast.makeText(context, "Vui Lòng Chọn Số Ly Bạn Muốn Mua", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sizeOfCup==-1)
                {
                    Toast.makeText(context, "Vui Lòng Chọn Size Cho Ly Bạn Uống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Duong==-1)
                {
                    Toast.makeText(context, "Vui Lòng Chọn Lượng Đường Cho Ly Bạn Uống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Da ==-1)
                {
                    Toast.makeText(context, "Vui Lòng Chọn Lượng Đá Cho Ly Bạn Uống", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog.dismiss();
                showConfirmDialog(position,txt_count.getNumber(),sizeOfCup,Duong,Da);
            }
        });

        //builder.show();
        AlertDialog dialog=builder.create();
        dialog.show();
        Button button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        int mau = Color.parseColor("#b22830");
        button.setBackgroundColor(mau);
    }
    private void showConfirmDialog(final int position, final String Number, int sizeOfCup, int Duong, int Da)
    {
        AlertDialog.Builder builder1= new AlertDialog.Builder(context);
        View itemview=LayoutInflater.from(context).inflate(R.layout.confirm_to_cart_layout,null);

        ImageView image_configm_cart = itemview.findViewById(R.id.imageViewConfirmCart);
        final TextView textviewTenThucDonConfirmCart = itemview.findViewById(R.id.tvTenThucDonConfirmCart);
        final TextView textviewGiaThucDonConfirmCart = itemview.findViewById(R.id.tvGiaThucDonConfirmCart);
        final TextView textviewDuongConfirmCart      = itemview.findViewById(R.id.tvDuongConfirmCart);
        final TextView textviewDaConfirmCart         = itemview.findViewById(R.id.tvDaConfirmCart);


        StringBuilder detailChiTietCart = new StringBuilder();
        detailChiTietCart.append(list.get(position).getTenMon())
                .append(" "+Number+" ");
        if(sizeOfCup == 0)
        {
            detailChiTietCart.append("Size of cup : S");
        }
        if(sizeOfCup == 1)
        {
            detailChiTietCart.append("Size of cup : M");
        }
        if(sizeOfCup == 2)
        {
            detailChiTietCart.append(" Size of cup : L");
        }

        Picasso.with(context).load(list.get(position).getHinhAnh()).into(image_configm_cart);
        textviewTenThucDonConfirmCart.setText(detailChiTietCart);
        textviewDaConfirmCart.setText(new StringBuilder().append(Da));
        textviewDuongConfirmCart.setText(new StringBuilder().append(Duong));
        Double Price = list.get(position).getGiaMon()*Double.parseDouble(Number);
        if(sizeOfCup == 1)
        {
            Price = Price + (6000 * Integer.parseInt(Number));
        }
        else if(sizeOfCup == 2)
        {
            Price = Price + (10000 * Integer.parseInt(Number));
        }
        textviewGiaThucDonConfirmCart.setText(Price.toString());

        //Sự kiện Nhấn Confirm Cart
        builder1.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cart cartItem=new Cart();
                cartItem.name=textviewTenThucDonConfirmCart.getText().toString();
                cartItem.amount=Integer.parseInt(Number);
                cartItem.da=Integer.parseInt(textviewDuongConfirmCart.getText().toString());
                cartItem.duong=Integer.parseInt(textviewDaConfirmCart.getText().toString());
                cartItem.price=Double.parseDouble(textviewGiaThucDonConfirmCart.getText().toString());
                cartItem.HinhAnh=list.get(position).getHinhAnh();
                Server.cartRepository.insertToCart(cartItem);

//                Log.d("ABCDEF",new Gson().toJson(cartItem));
//                Log.d("SoLuongTrongCarrt",""+Server.cartRepository.CountCartItems());
                Toast.makeText(context, "cartItem"+cartItem.name, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Save item success", Toast.LENGTH_SHORT).show();

            }
        });
        builder1.setView(itemview);
        //builder.show();
        AlertDialog dialog=builder1.create();
        dialog.show();
        Button button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        int mau = Color.parseColor("#b22830");
        button.setBackgroundColor(mau);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
