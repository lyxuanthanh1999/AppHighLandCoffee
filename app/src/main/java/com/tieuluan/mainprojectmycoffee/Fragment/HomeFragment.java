package com.tieuluan.mainprojectmycoffee.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.Database.DataSource.CartRepository;
import com.tieuluan.mainprojectmycoffee.Database.Local.CartDataSource;
import com.tieuluan.mainprojectmycoffee.Database.Local.CartDatabase;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapter;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterTinTuc;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterTrachNhiemCongDong;
import com.tieuluan.mainprojectmycoffee.model.ArrrayListLoaiMon;
import com.tieuluan.mainprojectmycoffee.model.LoaiMon;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;
import com.tieuluan.mainprojectmycoffee.model.TrachNhiemCongDong;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewLoaiMon;
    RecyclerView recyclerViewtintuc;
    RecyclerView recyclerViewTNCD;


    // DrawerLayout drawerLayout;

    TinTuc tinTuc = new TinTuc();
    LoaiMon loaiMon=new LoaiMon();
    TrachNhiemCongDong tncd = new TrachNhiemCongDong();

    ArrayList<TinTuc> listTinTuc = new ArrayList<>();
    ArrrayListLoaiMon list =new ArrrayListLoaiMon();
    ArrayList<TrachNhiemCongDong> listTNCD = new ArrayList<>();

    //Adapter
    RecAdapter recLoaiMons;
    RecAdapterTinTuc recTinTucs;
    RecAdapterTrachNhiemCongDong recTNCD;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper=view.findViewById(R.id.viewflipper);
        recyclerViewLoaiMon=view.findViewById(R.id.loaimonrec);
        recyclerViewtintuc=view.findViewById(R.id.tintucrec);
        recyclerViewTNCD = view.findViewById(R.id.tracnhiemrec);
        layAPILoaiMon();
        layAPITinTuc();
        layAPITNCD();
        ActionViewFlipper();
        initDB();
        return view;
    }
    private void initDB()
    {
        Server.cartDatabase = CartDatabase.getInstance(getContext());

        Server.cartRepository = CartRepository.getInstance(CartDataSource.getInstance(Server.cartDatabase.cartDAO()));
    }
    private void layAPITinTuc()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String strAPI=String.format(Server.getTinTuc);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //loaiMons=new ArrayList<>();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("tintucs");
                    JSONObject obj;
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        tinTuc=new TinTuc();
                        obj=jsonArray.getJSONObject(i);
                        tinTuc.setMaTin(obj.getInt("MaTin"));
                        tinTuc.setTieuDe(obj.getString("TieuDe"));
                        tinTuc.setNoiDung(obj.getString("NoiDung"));
                        tinTuc.setAnhTinTuc(obj.getString("AnhTinTuc"));
                        String ngaydang=obj.getString("NgayDang");
                        Date date = new  SimpleDateFormat("dd-MM-yyyy").parse(ngaydang);
                        tinTuc.setNgayDang(date);
                        listTinTuc.add(tinTuc);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }

                recTinTucs =new RecAdapterTinTuc(listTinTuc,getContext());
                recyclerViewtintuc.setAdapter(recTinTucs);

                recyclerViewtintuc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                recyclerViewtintuc.setHasFixedSize(true);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),""+error,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void layAPILoaiMon()
    {

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String strAPI=String.format(Server.getLoaiMon);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //loaiMons=new ArrayList<>();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("loaimons");
                    JSONObject obj;
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        loaiMon=new LoaiMon();
                        obj=jsonArray.getJSONObject(i);
                        loaiMon.setMaLoaiMon(obj.getInt("MaLoaiMon"));
                        loaiMon.setTenLoaiMon(obj.getString("TenLoaiMon"));
                        loaiMon.setMoTa(obj.getString("MoTa"));
                        loaiMon.setHinhAnh(obj.getString("HinhAnh"));
                        // loaiMons.add(loaiMon);
                        list.addLoaiMon(loaiMon);
                    }} catch (JSONException e) {
                    e.printStackTrace();
                }
                recLoaiMons =new RecAdapter(list.getList(),getContext());
                recyclerViewLoaiMon.setAdapter(recLoaiMons);
                //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewLoaiMon.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                recyclerViewLoaiMon.setHasFixedSize(true);

//                GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
//                recyclerView.setLayoutManager(gridLayoutManager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),""+error,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void layAPITNCD()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        String strAPI=String.format(Server.getTrachNhiemCongDong);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strAPI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("trachnhiemcongdongs");
                    JSONObject jsonObject1;
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        jsonObject1 = jsonArray.getJSONObject(i);
                        tncd = new TrachNhiemCongDong();
                        tncd.setMaTNCD(jsonObject1.getInt("MaTNCD"));
                        tncd.setTieuDe(jsonObject1.getString("TieuDe"));
                        tncd.setNoiDung(jsonObject1.getString("NoiDung"));
                        tncd.setAnhTinTuc(jsonObject1.getString("AnhTinTuc"));
                        listTNCD.add(tncd);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                 recTNCD = new RecAdapterTrachNhiemCongDong(listTNCD, getContext());
                recyclerViewTNCD.setAdapter(recTNCD);
                recyclerViewTNCD.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                recyclerViewTNCD.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void ActionViewFlipper()
    {
        ArrayList<String> mangQuangCao=new ArrayList<>();
        mangQuangCao.add("https://www.highlandscoffee.com.vn/vnt_upload/weblink/HL20_2000x639_1.png");
        mangQuangCao.add("https://www.highlandscoffee.com.vn/vnt_upload/weblink/HCO-7548-PHIN-SUA-DA-2019-TALENT-WEB_1.jpg");
        mangQuangCao.add("https://www.highlandscoffee.com.vn/vnt_upload/weblink/HCO-7605-FESTIVE-2020-WEB-FB-2000X639_1.png");
        mangQuangCao.add("https://www.highlandscoffee.com.vn/vnt_upload/weblink/VIET.Brand_Campaign_WEBBANNER.jpg");
        for(int i = 0;i< mangQuangCao.size();i++)
        {
            ImageView imageView=new ImageView(getContext());
            Picasso.with(getContext()).load(mangQuangCao.get(i)).into(imageView);
            //Picasso.with(BannerAdvertisementActivity.this).load(mangQuangCao.get(i)).into(imageView);
            //chỉnh Ảnh
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        Animation animation_slideIn= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
        Animation animation_slideOut= AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slideIn);
        viewFlipper.setInAnimation(animation_slideOut);
        //set thời gian cho viewflipper chuyển động
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }
}
