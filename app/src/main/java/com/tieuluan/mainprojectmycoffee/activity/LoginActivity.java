package com.tieuluan.mainprojectmycoffee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tieuluan.mainprojectmycoffee.R;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private EditText edtuser,edtpassword;
    private Button btnlogin;
    private ProgressBar progressBarLogin;
    private TextView forgotpassword,signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser=findViewById(R.id.edtUserSignIn);
        edtpassword=findViewById(R.id.edtPasswordSignIn);
        progressBarLogin=findViewById(R.id.progressBarLogin);
        forgotpassword=findViewById(R.id.tvForgetPassword);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        signup=findViewById(R.id.tvSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnlogin=findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhap();
            }
        });
        edtuser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                kiemTraDauVao();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                kiemTraDauVao();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
    }
    public void kiemTraDauVao()
    {
        if(!(TextUtils.isEmpty(edtuser.getText())) ) {
            if(!(TextUtils.isEmpty(edtpassword.getText())) ) {
                btnlogin.setEnabled(true);
            }
            else
            {
                btnlogin.setEnabled(false);
            }
        }
        else
        {
            btnlogin.setEnabled(false);
        }
    }
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public void dangNhap()
    {
        if(edtuser.getText().toString().matches(emailPattern))
        {
            if(edtpassword.getText().toString().length()>=8)
            {
                progressBarLogin.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(edtuser.getText().toString(),edtpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent=new Intent(LoginActivity.this,BannerAdvertisementActivity.class);
                                    LuuDuLieu(edtuser.getText().toString(),edtpassword.getText().toString());
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this,"login oke",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    progressBarLogin.setVisibility(View.INVISIBLE);
                                    btnlogin.setEnabled(false);
                                    String Error=task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this,Error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else
            {
                edtpassword.setError("Password trên 8 ký tự");
            }
        }
        else
        {
                edtuser.setError("User sai định dạng email");
        }
    }

    private void LuuDuLieu(String user,String Password)
    {
        khoiToaDuLieu();
        sharedPreferences=getSharedPreferences("My-Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("user",user);
        editor.putString("pass",Password);
        editor.apply();
    }
    private void khoiToaDuLieu()
    {
        sharedPreferences=getSharedPreferences("My-Data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser=firebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
            Intent intent=new Intent(LoginActivity.this,BannerAdvertisementActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Mời Bạn Đăng Nhập", Toast.LENGTH_LONG).show();
        }
    }
}
