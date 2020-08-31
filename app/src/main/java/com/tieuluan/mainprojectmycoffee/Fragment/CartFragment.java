package com.tieuluan.mainprojectmycoffee.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterCart;
import com.tieuluan.mainprojectmycoffee.model.DatHang;
import com.tieuluan.mainprojectmycoffee.ultil.RecycleViewTouchHelper;
import com.tieuluan.mainprojectmycoffee.ultil.RecycleViewTouchHelperListener;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class CartFragment extends Fragment implements RecycleViewTouchHelperListener {
    RecyclerView recyclerViewCart;
    Button buttonPlaceHolder;
    RecAdapterCart recAdapterCart;
    List<Cart> LocalCarts = new ArrayList<>();
    RelativeLayout rootLayout;
    //=================
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

   // String ortherDetail;
    String mdt = "";
    CompositeDisposable compositeDisposable;

    public CartFragment() {
        // Required empty public constructor

    }
    final DatHang datHang = new DatHang();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cart, container, false);

        compositeDisposable=new CompositeDisposable();



        recyclerViewCart = view.findViewById(R.id.recycleViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCart.setHasFixedSize(true);

        buttonPlaceHolder = view.findViewById(R.id.btn_place_holder);

        rootLayout = view.findViewById(R.id.rootLayout);
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerViewCart);


        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(getContext(), ""+firebaseAuth.getInstance().getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        //lấy mã đặt hàng

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String strApi = String.format(getString(R.string.getMaDatHang),firebaseAuth.getInstance().getCurrentUser().getUid());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("madathangs");
                    JSONObject obj;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        obj = jsonArray.getJSONObject(i);
                        datHang.setOrtherId(Integer.parseInt(obj.getString("orderId")));

                    }
                    Toast.makeText(getContext(), "mã đặt hàng  : "+datHang.getOrtherId(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "không lấy được mã đặt hàng", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);


        loadCartItem();
        ThemHoaDon();
        return view;
    }
    private void ThemHoaDon()
    {

                buttonPlaceHolder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(Server.cartRepository.CountCartItems()>0)
                        {
                        dialogOrder();
                        }
                        else {
                            Toast.makeText(getContext(), "Giỏ hàng bạn chưa có sản phẩm nào cả", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

         }
    private void dialogOrder()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xác Nhận Đặt Hàng ");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.submit_dathang_layout,null);
        final EditText edtcomment=view.findViewById(R.id.edt_Comment);
        final EditText edtdiachi=view.findViewById(R.id.edt_DiaChi);
        builder.setView(view);
        builder.setNegativeButton("Hủy Bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String comment=edtcomment.getText().toString();
                final String address=edtdiachi.getText().toString();
                if(comment.isEmpty()|| address.isEmpty())
                {
                    Toast.makeText(getContext(), "vui lòng điền đầy đủ thông tin để đặt hàng !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {

                    GuiDatHangLenServer(comment,address);
                    GuiDatHangChiTiet();
                    Server.cartRepository.emptyCart();
                    LocalCarts.isEmpty();
                }
            }
        });
        builder.show();
    }
    private void GuiDatHangChiTiet() {
       final List<Cart>list=new ArrayList<>();
        for(Cart item : LocalCarts)
        {
            list.add(item);
        }
        for( final Cart item : list)
        {
            RequestQueue queue1 = Volley.newRequestQueue(getContext());
            final String urlApi1 = String.format(Server.postGioHangLenChiTietDatHang);
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, urlApi1, new Response.Listener<String>() {
                @Override
                public void onResponse(String response1) {
                    long mdh =  datHang.getOrtherId();
                    Toast.makeText(getContext(), "HOÀN TẤT : "+response1 + " : "+mdh, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    HashMap<String,String> map = new HashMap<>();
                       Cart item1 = new Cart();
                    item1 = item;
                        long mdh =  datHang.getOrtherId() + 1;
                        map.put("madathang", String.valueOf(mdh));
                        map.put("mamon", String.valueOf(item.id));
                        map.put("tenmon", item.name);
                        map.put("soluong",String.valueOf( item.amount));
                        map.put("gia", String.valueOf(item.price));
                    return  map;
                }
            };
            queue1.add(stringRequest1);
        }
    }
    private void GuiDatHangLenServer( final String comment, final String address)
    {

        //chi tiet dat hang la danh sach ==> carts


        //ortherDetail = carts.toString();
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getInstance().getCurrentUser();
        RequestQueue queue= Volley.newRequestQueue(getContext());
        final String urlApi= String.format(Server.postGioHangLenDatHang);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getContext(), "Gửi Đặt Hàng Đã Lên Sever Thành Công " + response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("orderComment",comment);
                map.put("orderAddress",address);
                map.put("userName",currentUser.getEmail());
                map.put("UID",currentUser.getUid());
                return map;
            }
        };
        queue.add(stringRequest);
    }
    private void loadCartItem()
    {
        compositeDisposable.add(Server.cartRepository.getCartItems()
        .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<List<Cart>>() {
                    @Override
                    public void accept(List<Cart> carts) throws Exception {
                        hienThiCartItem(carts);
                    }
                }));
    }
    private void hienThiCartItem(List<Cart> carts)
    {
        LocalCarts=carts;
        recAdapterCart = new RecAdapterCart(carts,getContext());
        recyclerViewCart.setAdapter(recAdapterCart);
    }

    @Override
    public void onResume() {
        loadCartItem();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
    //Sự kiện xóa giỏ hàng
    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int postition) {
        if(viewHolder instanceof RecAdapterCart.VH)
        {
            String name = LocalCarts.get(viewHolder.getAdapterPosition()).name;

            final Cart deleteItem = LocalCarts.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            //Xóa giỏ hàng trong adaptercart
            recAdapterCart.removeItem(deleteIndex);
            //Xóa Giỏ hàng trong roomDatabase
            Server.cartRepository.DeleteCartItem(deleteItem);

            Snackbar snackbar = Snackbar.make(rootLayout
                                            ,new StringBuilder(name).append(" xóa từ danh sách giỏ hàng").toString()
                                            ,Snackbar.LENGTH_SHORT);
            snackbar.setAction("Khôi phục", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recAdapterCart.restoreItem(deleteItem,deleteIndex);
                    Server.cartRepository.insertToCart(deleteItem);
                }
            });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }
}
