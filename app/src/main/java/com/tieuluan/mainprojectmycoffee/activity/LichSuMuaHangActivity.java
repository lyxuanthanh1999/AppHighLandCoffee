package com.tieuluan.mainprojectmycoffee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterLichSuMuaHang;
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterTinTuc;
import com.tieuluan.mainprojectmycoffee.model.ChiTietDatHang;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LichSuMuaHangActivity extends AppCompatActivity {
    RecyclerView recycleViewLichSuMuaHang;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;
    ChiTietDatHang chiTietDatHang;
    RecAdapterLichSuMuaHang recAdapterLichSuMuaHang;
    List<ChiTietDatHang> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);
        recycleViewLichSuMuaHang = findViewById(R.id.recLichSuMuaHang);

        init();
    }
    private void init()
    {
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(LichSuMuaHangActivity.this);
        String strApi = String.format(getString(R.string.getChiTietDatHang),uid);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("lichsumuahangs");
                    JSONObject obj;
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        obj=jsonArray.getJSONObject(i);
                        chiTietDatHang = new ChiTietDatHang();
                        chiTietDatHang.setOrderStatus(obj.getInt("orderStatus"));
                        chiTietDatHang.setOrderComment(obj.getString("orderComment"));
                        chiTietDatHang.setOrderAddress(obj.getString("orderAddress"));
                        chiTietDatHang.setSoLuong(obj.getInt("soluong"));
                        chiTietDatHang.setGia(obj.getDouble("gia"));
                        chiTietDatHang.setTenMon(obj.getString("tenmon"));
                        list.add(chiTietDatHang);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                Toast.makeText(LichSuMuaHangActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                recAdapterLichSuMuaHang =new RecAdapterLichSuMuaHang(list,getApplication());
                recycleViewLichSuMuaHang.setAdapter(recAdapterLichSuMuaHang);

                recycleViewLichSuMuaHang.setLayoutManager(new LinearLayoutManager(LichSuMuaHangActivity.this, LinearLayoutManager.HORIZONTAL,false));
                recycleViewLichSuMuaHang.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
