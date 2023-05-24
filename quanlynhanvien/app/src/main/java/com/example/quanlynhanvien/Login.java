package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtName,edtPass;
    ImageView imgUser,imgPass;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Đăng Nhập");
        addControl();
        addEvents();
    }
    public void addControl(){
        edtName = findViewById(R.id.edtlgTen);
        edtPass = findViewById(R.id.edtlgPass);
        imgUser = findViewById(R.id.imguser);
        imgPass = findViewById(R.id.imgpass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        imgUser.setBackground(getDrawable(R.drawable.user));
        imgPass.setBackground(getDrawable(R.drawable.pass));

    }
    public void addEvents(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangnhap();
            }
        });
    }
    public void Dangnhap(){
        String user = "admin";
        String pass = "123456";
        if(user.equals(edtName.getText().toString()) && pass.equals(edtPass.getText().toString())){
            Intent intent = new Intent(Login.this,ManHinhChinh.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
        }
    }

}