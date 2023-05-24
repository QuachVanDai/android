package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ManHinhChinh extends AppCompatActivity {
        ImageView imgThanhVien,imgThemThanhVien,imgLich,imgDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        addControl();
        Events();
    }
    public void addControl(){
        imgThanhVien = findViewById(R.id.imgMember);
        imgThemThanhVien = findViewById(R.id.imgAdd);
        imgLich = findViewById(R.id.imgLich);
        imgDangXuat = findViewById(R.id.imgLogOut);
        imgThanhVien.setImageResource(R.drawable.member);
        imgLich.setImageResource(R.drawable.lich);
        imgThemThanhVien.setImageResource(R.drawable.adduser);
    }
    public void Events(){
        imgThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinh.this, danhsachnhanvien.class);
                startActivity(intent);
            }
        });
        imgLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinh.this,ManHinhChinh.class);
                startActivity(intent);
            }
        });

        imgThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinh.this,addnhanvien.class);
                startActivity(intent);
            }
        });

        imgDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChinh.this,Login.class);
                startActivity(intent);
            }
        });

    }

}