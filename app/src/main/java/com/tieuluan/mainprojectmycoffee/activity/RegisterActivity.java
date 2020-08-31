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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tieuluan.mainprojectmycoffee.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtemailSignUp,edtuserSignUp,edtpasswordSignUp,edtrepasswordSignUp;
    private Button btnsignUp;
    private ProgressBar progressbarSignUp;
    private TextView tvorSignUp,tvallreadyhaveaccountSignUp;
    private ImageView logosignUp;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(RegisterActivity.this,"Oke",Toast.LENGTH_SHORT).show();
        init();
    }
    public void init()
    {
        btnsignUp=findViewById(R.id.btnSignUp);
        edtemailSignUp=findViewById(R.id.EdtEmailSignUp);
        edtuserSignUp=findViewById(R.id.EdtUserSignUp);
        edtpasswordSignUp=findViewById(R.id.EdtPasswordSignUp);
        edtrepasswordSignUp=findViewById(R.id.EdtRePasswordSignUp);
        progressbarSignUp=findViewById(R.id.progressBarSignUp);
        firebaseAuth = FirebaseAuth.getInstance();
        tvallreadyhaveaccountSignUp=findViewById(R.id.tvAllReadyHaveAccount);
        tvallreadyhaveaccountSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Thực hiện firebase
                KiemTraEmail();

            }
        });
        //Sự kiện thêm chuỗi đã bị thay đổi
        edtemailSignUp.addTextChangedListener(new TextWatcher() {
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
        edtuserSignUp.addTextChangedListener(new TextWatcher() {
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
        edtpasswordSignUp.addTextChangedListener(new TextWatcher() {
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
        edtrepasswordSignUp.addTextChangedListener(new TextWatcher() {
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
    }
    public void kiemTraDauVao()
    {
        if(!(TextUtils.isEmpty(edtemailSignUp.getText())) ) {
            if(!(TextUtils.isEmpty(edtuserSignUp.getText())) ){
                if(!(TextUtils.isEmpty(edtpasswordSignUp.getText())) && edtpasswordSignUp.length()>=8 ){
                    if(!(TextUtils.isEmpty(edtrepasswordSignUp.getText())) ){
                            btnsignUp.setEnabled(true);
                    }
                    else
                    {
                        btnsignUp.setEnabled(false);
                    }
                }
                else
                {
                    btnsignUp.setEnabled(false);
                }
            }
            else
            {
                btnsignUp.setEnabled(false);
            }
        }
    }
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public void KiemTraEmail()
    {
        if(edtemailSignUp.getText().toString().matches(emailPattern))
        {
            if(edtpasswordSignUp.getText().toString().equals(edtrepasswordSignUp.getText().toString()))
            {
                progressbarSignUp.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(edtemailSignUp.getText().toString(),edtpasswordSignUp.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else
                                {
                                    progressbarSignUp.setVisibility(View.INVISIBLE);
                                    String error=task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this,error,Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
            else
            {
                edtpasswordSignUp.setError("Không khớp mật khẩu");
            }
        }
        else
        {
                edtemailSignUp.setError("Email Không Hợp lệ");
        }
    }

    public static boolean isValid(String email)
    {
        String emailregex="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pat=Pattern.compile(emailregex);
        if(email==null)
        {
            return false;
        }
        Matcher matcher;

        return pat.matcher(email).matches();

    }
}

