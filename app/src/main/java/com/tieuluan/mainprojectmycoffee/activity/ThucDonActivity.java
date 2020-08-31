package com.tieuluan.mainprojectmycoffee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.adapter.recAdapterThucDon;
import com.tieuluan.mainprojectmycoffee.model.ArrayListThucDon;
import com.tieuluan.mainprojectmycoffee.model.ThucDon;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThucDonActivity extends AppCompatActivity {

    ArrayListThucDon list=new ArrayListThucDon();
    recAdapterThucDon recAdapterThucDon;
    RecyclerView recyclerViewThucDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuc_don);
        recyclerViewThucDon=findViewById(R.id.recThucDon);

        init();
    }
    public void init()
    {
        int MaLoaiMon = 0;
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        if(b!=null)
        {
            MaLoaiMon=Integer.parseInt(intent.getStringExtra("MaMon").trim());
        }
        RequestQueue requestQueue = Volley.newRequestQueue(ThucDonActivity.this);
        String strApi = String.format(getString(R.string.getThucDon),MaLoaiMon);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("thucdons");
                    JSONObject obj;
                    for (int i =0; i < jsonArray.length(); i++)
                    {
                        ThucDon thucDon=new ThucDon();
                        obj=jsonArray.getJSONObject(i);
                        thucDon.setMaMon(obj.getInt("MaMon"));
                        thucDon.setMaLoaiMon(obj.getInt("MaLoaiMon"));
                        thucDon.setTenMon(obj.getString("TenMon"));
                        thucDon.setGiaMon(obj.getInt("GiaMon"));
                        thucDon.setMoTa(obj.getString("MoTa"));
                        thucDon.setHinhAnh(obj.getString("HinhAnh"));
                        list.addThucDon(thucDon);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recAdapterThucDon=new recAdapterThucDon(ThucDonActivity.this,list.getList());
                recyclerViewThucDon.setAdapter(recAdapterThucDon);

                recyclerViewThucDon.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
                recyclerViewThucDon.setLayoutManager(gridLayoutManager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThucDonActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
