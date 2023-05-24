package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {
    final String DATABASE_NAME = "qlnhanvien5.sqlite";
    int id=-1;
    TextView txtTeninfo, txtSdtinfo, txtChucVuinfo,txtGhiChuinfo,
            txtDiaChiinfo,txtGioitinhinfo,txtSoNgaylamInfo,txtLuongInfo,txtTongLuongInfo;
    ImageView imgHinhDaiDien;
    Button btnBackInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        addControls();
        addEvent();
        initUI();
    }
    private void addEvent(){
        btnBackInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               backInfo();
            }
        });
    }
    private void backInfo(){
        Intent intent = new Intent(this, danhsachnhanvien.class);
        startActivity(intent);
    }

    private void addControls(){
        btnBackInfo = findViewById(R.id.btnBackInfo);
        txtTeninfo = findViewById(R.id.txtTenInfo);
        txtSdtinfo =  findViewById(R.id.txtSDTInfo);
        txtChucVuinfo = findViewById(R.id.txtChucVuInfo);
        txtDiaChiinfo =  findViewById(R.id.txtDiaChiInfo);
        txtGioitinhinfo =  findViewById(R.id.txtGioiTinhInfo);
        txtGhiChuinfo = findViewById(R.id.txtGhiChuInfo);
        txtLuongInfo = findViewById(R.id.txtLuongInfo);
        txtTongLuongInfo = findViewById(R.id.txtTongLuongInfo);
        txtSoNgaylamInfo = findViewById(R.id.txtSonNgayLamInfo);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgInfo);
    }
    private void initUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        SQLiteDatabase database1 = database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database1.rawQuery("SELECT * FROM nhanvien5 WHERE ID = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String gioiTinh = cursor.getString(2);
        String DiaChi = cursor.getString(3);
        String sdt = cursor.getString(4);
        String chucVu = cursor.getString(5);
        int luong = cursor.getInt(6);
        int songaylam = cursor.getInt(7);
        byte[] anh = cursor.getBlob(8);
        String ghiChu = cursor.getString(9);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imgHinhDaiDien.setImageBitmap(bitmap);
        txtSdtinfo.setText(sdt);
        txtTeninfo.setText(ten);
        txtGhiChuinfo.setText(ghiChu);
        txtChucVuinfo.setText(chucVu);
        txtDiaChiinfo.setText(DiaChi);
        txtGioitinhinfo.setText(gioiTinh);
        txtLuongInfo.setText(String.valueOf(luong));
        txtTongLuongInfo.setText(String.valueOf(luong * songaylam));
        txtSoNgaylamInfo.setText(String.valueOf(songaylam));
    }
}