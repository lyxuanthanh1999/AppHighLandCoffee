package com.tieuluan.mainprojectmycoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tieuluan.mainprojectmycoffee.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText usergmail;
    ImageView imageforgot;
    Button reset;
    TextView tvComback;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        imageforgot = findViewById(R.id.imageForgot);
        firebaseAuth=FirebaseAuth.getInstance();
        usergmail=findViewById(R.id.edtUserGmailForgot);
        usergmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        KiemTraDauVao();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tvComback=findViewById(R.id.tvComeBackLogin);
        tvComback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        reset=findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemTraEmail();
                firebaseAuth.sendPasswordResetEmail(usergmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotPasswordActivity.this,"Email Đã Được Gửi",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String error=task.getException().getMessage();
                            Toast.makeText(ForgotPasswordActivity.this,error,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void KiemTraDauVao()
    {
        if(TextUtils.isEmpty(usergmail.getText()))
        {
            reset.setEnabled(false);
        }
        else
        {
            reset.setEnabled(true);
        }
    }
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private void kiemTraEmail()
    {
        if(!usergmail.getText().toString().matches(emailPattern))
        {
            usergmail.setError("Nhập Sai Định Dạng Email");
            return;
        }
    }

}
