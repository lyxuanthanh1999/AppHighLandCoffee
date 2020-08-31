package com.tieuluan.mainprojectmycoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.tieuluan.mainprojectmycoffee.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=findViewById(R.id.imageViewLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LogIntent=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(LogIntent);
                finish();
            }
        },3000);
    }
}
