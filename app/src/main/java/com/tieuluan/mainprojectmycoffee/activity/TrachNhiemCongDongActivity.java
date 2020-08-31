package com.tieuluan.mainprojectmycoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;
import com.tieuluan.mainprojectmycoffee.model.TrachNhiemCongDong;
import com.tieuluan.mainprojectmycoffee.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrachNhiemCongDongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trach_nhiem_cong_dong);
        init();
    }
    private void init()
    {
        int MaTNCD=0;
        Intent intent =getIntent();
        Bundle bd =intent.getExtras();
        bd.getString("MaTNCD");
        if(bd!=null)
        {
            MaTNCD = Integer.parseInt(bd.getString("MaTNCD").trim());
        }
        final ImageView imagedetail_TNCD = findViewById(R.id.image_detail_TNCD);
        final TextView textviewdetail_tieuDeNoiDung_TNCD = findViewById(R.id.textview_detail_tieuDeNoiDung_TNCD);
        final TextView textviewdetail_noiDung_TNCD = findViewById(R.id.textview_detail_noiDung_TNCD);

        RequestQueue requestQueue = Volley.newRequestQueue(TrachNhiemCongDongActivity.this);
        String strApi = String.format(getString(R.string.getTNCD),MaTNCD);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TrachNhiemCongDong TNCD   = new TrachNhiemCongDong();
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("TrachNhiemCongDongs");
                    JSONObject obj;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        obj = jsonArray.getJSONObject(i);
                        TNCD.setMaTNCD(obj.getInt("MaTNCD"));
                        TNCD.setTieuDe(obj.getString("TieuDe"));
                        TNCD.setNoiDung(obj.getString("NoiDung"));
                        TNCD.setAnhTinTuc(obj.getString("AnhTinTuc"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Picasso.with(TrachNhiemCongDongActivity.this).load(TNCD.getAnhTinTuc()).into(imagedetail_TNCD);
                imagedetail_TNCD.setScaleType(ImageView.ScaleType.FIT_XY);
                textviewdetail_tieuDeNoiDung_TNCD.setText(TNCD.getTieuDe());
                textviewdetail_noiDung_TNCD.setText(TNCD.getNoiDung());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrachNhiemCongDongActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }

}
