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
import com.tieuluan.mainprojectmycoffee.adapter.RecAdapterTinTuc;
import com.tieuluan.mainprojectmycoffee.model.ThucDon;
import com.tieuluan.mainprojectmycoffee.model.TinTuc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TinTucActivity extends AppCompatActivity {

    TextView tvDtNgayDang,TieuDtDeNoiDung,DtNoiDung;
    ImageView imageDt;
    List<TinTuc> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);
        init();
    }
    private void init()
    {
        int MaTinTuc=0;
        Intent intent =getIntent();
        Bundle bd =intent.getExtras();
        bd.getString("MaTinTuc");
        if(bd!=null)
        {
            MaTinTuc = Integer.parseInt(bd.getString("MaTinTuc").trim());
        }

        tvDtNgayDang = findViewById(R.id.textview_detail_ngayDang);
        TieuDtDeNoiDung = findViewById(R.id.textview_detail_tieuDeNoiDung);
        DtNoiDung = findViewById(R.id.textview_detail_noiDung);
        imageDt = findViewById(R.id.image_detail_tinTuc);

        RequestQueue requestQueue = Volley.newRequestQueue(TinTucActivity.this);
        String strApi = String.format(getString(R.string.getTinTuc),MaTinTuc);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TinTuc tinTuc   = new TinTuc();
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("TinTucs");
                    JSONObject obj;
                    for (int i = 0; i < jsonArray.length(); i++) {

                        obj = jsonArray.getJSONObject(i);
                        tinTuc.setMaTin(obj.getInt("MaTin"));
                        tinTuc.setTieuDe(obj.getString("TieuDe"));
                        tinTuc.setNoiDung(obj.getString("NoiDung"));
                        tinTuc.setAnhTinTuc(obj.getString("AnhTinTuc"));
                        String ngaydang=obj.getString("NgayDang");
                        Date date = new  SimpleDateFormat("dd-MM-yyyy").parse(ngaydang);
                        tinTuc.setNgayDang(date);
                        //list.add(tinTuc);
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                Picasso.with(TinTucActivity.this).load(tinTuc.getAnhTinTuc()).into(imageDt);
                imageDt.setScaleType(ImageView.ScaleType.FIT_XY);
                DtNoiDung.setText(tinTuc.getNoiDung());
                TieuDtDeNoiDung.setText(tinTuc.getTieuDe());
                tvDtNgayDang.setText(tinTuc.getNgayDang().toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TinTucActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
