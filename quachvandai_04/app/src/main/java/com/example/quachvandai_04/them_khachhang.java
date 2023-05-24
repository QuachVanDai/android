package com.example.quachvandai_04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class them_khachhang extends AppCompatActivity {
    Button  btnThem, btnQuayLai;
    EditText edtTen, edtSoPhong, edtDonGia,edtSoNgayLuutru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khachhang);
        addControls();
        addEvents();
    }
    private void addControls() {
        btnThem = (Button) findViewById(R.id.btnThem);
        btnQuayLai = (Button) findViewById(R.id.btnQuayLai);
        edtTen = (EditText) findViewById(R.id.editTen);
        edtSoPhong = (EditText) findViewById(R.id.editSoPhong);
        edtDonGia = (EditText) findViewById(R.id.editDonGia);
        edtSoNgayLuutru = (EditText) findViewById(R.id.editSoNgayLT);
    }
    public void addEvents(){
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(them_khachhang.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them_khach_hang();
            }
        });
    }
    public void them_khach_hang(){
        String ten = edtTen.getText().toString();
        String soPhong = edtSoPhong.getText().toString();
        String donGia = edtDonGia.getText().toString();
        String soNgayLuuTru = edtSoNgayLuutru.getText().toString();
        if(ten.equals("")){
            Toast.makeText(them_khachhang.this,"Không bỏ trống tên",Toast.LENGTH_SHORT).show();
        }
        else  if(edtSoPhong.equals("")){
            Toast.makeText(them_khachhang.this,"Không bỏ trống số phòng",Toast.LENGTH_SHORT).show();
        }
        else  if(donGia.equals("")){
            Toast.makeText(them_khachhang.this,"Không bỏ trống đơn giá",Toast.LENGTH_SHORT).show();
        }
        else  if(soNgayLuuTru.equals("")){
            Toast.makeText(them_khachhang.this,"Không bỏ trống số lưu trú",Toast.LENGTH_SHORT).show();
        }
        else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hoten", ten);
            contentValues.put("sophong", soPhong);
            contentValues.put("dongia", donGia);
            contentValues.put("songayluutru", soNgayLuuTru);
            SQLiteDatabase database1 = database.initDatabase(this, "hoadonkhachsan.sqlite");
            database1.insert("hoadon_2409", null, contentValues);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
