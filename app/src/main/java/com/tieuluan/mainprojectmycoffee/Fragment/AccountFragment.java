package com.tieuluan.mainprojectmycoffee.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tieuluan.mainprojectmycoffee.R;
import com.tieuluan.mainprojectmycoffee.activity.LichSuMuaHangActivity;
import com.tieuluan.mainprojectmycoffee.activity.LoginActivity;
import com.tieuluan.mainprojectmycoffee.activity.MainActivity;
import com.tieuluan.mainprojectmycoffee.activity.RegisterActivity;


public class AccountFragment extends Fragment {

    Button btnLogOut;
    Button updatpassword;
    TextView useraccount;
    Button btnlsmh;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //dialog
    EditText password;
    EditText repassword;
    Button ChapNhan;
    Button Huy;
    //=================
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_account, container, false);
        btnLogOut = view.findViewById(R.id.buttonLogOut);
        useraccount=view.findViewById(R.id.userAccount);
        updatpassword = view.findViewById(R.id.buttonCapNhapPassword);
        btnlsmh = view.findViewById(R.id.buttonLichSuMuaHang);
        btnlsmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LichSuMuaHangActivity.class);
                startActivity(intent);
            }
        });
        String name = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        useraccount.setText("Tài Khoản : "+name);

        updatpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser=firebaseAuth.getInstance().getCurrentUser();
                if(currentUser!=null)
                {
                    FirebaseAuth.getInstance().signOut();
//                    sharedPreferences=getActivity().getSharedPreferences("My-Data", Context.MODE_PRIVATE);
//                    editor.clear();
//                    editor.commit();
//                    editor.apply();
                    Intent intent=new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    Toast.makeText(getContext(), "Đăng Xuất Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    public void openDialog()
    {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setTitle("Thông Báo");
        dialog.setContentView(R.layout.dialog_cap_nhap_password);
        password = dialog.findViewById(R.id.edtpasswordcapnhap);
        repassword = dialog.findViewById(R.id.edtrepasswordcapnhap);
        ChapNhan=dialog.findViewById(R.id.btnUpdatePassword);
        Huy=dialog.findViewById(R.id.btnHuyUpdatePassword);
        ChapNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString()==""||repassword.getText().toString()==""
                        ||password.getText()== null||repassword.getText()== null || password.getText().length()<8 ||repassword.getText().length()<8)
                {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ và trên 8 ký tự!!!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                else
                {
                    if(!password.getText().toString().equals(repassword.getText().toString()))
                    {
                        Toast.makeText(getActivity(), "Mật khẩu bạn điền vào không trùng khớp", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }
                    else
                    {

                        String newpassword = password.getText().toString();
                        currentUser=firebaseAuth.getInstance().getCurrentUser();
                        currentUser.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getActivity(), "Đã Cập Nhập Mật Khẩu Thành Công ", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    return;
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Đã Cập Nhập Mật Khẩu Không Thành Công ", Toast.LENGTH_SHORT).show();

                            }
                        });;
                    }
                }

            }
        });
        Huy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                //getActivity().finish();
            }
        });
        dialog.show();
    }

}
