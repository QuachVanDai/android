package com.example.quachvandai_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sua_khachhang extends AppCompatActivity {
    Button btnSua, btnQuayLai;
    EditText edtTen, edtSoPhong, edtDonGia,edtSoNgayLuutru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_khachhang);
        addControls();
        addEvents();
        hien_du_lieu();
    }
    private void addControls() {
        btnSua = (Button) findViewById(R.id.btn_Sua);
        btnQuayLai = (Button) findViewById(R.id.btnQuayLai);
        edtTen = (EditText) findViewById(R.id.editTen_Sua);
        edtSoPhong = (EditText) findViewById(R.id.editSoPhong_Sua);
        edtDonGia = (EditText) findViewById(R.id.editDonGia_Sua);
        edtSoNgayLuutru = (EditText) findViewById(R.id.editSoNgayLT_Sua);
    }
    public void addEvents(){
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sua_khachhang.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sua_khach_hang();
            }
        });
    }
    public void hien_du_lieu(){
        Intent intent = getIntent();
        int ma = intent.getIntExtra("ma", -1);
        SQLiteDatabase database1 = database.initDatabase(this, "hoadonkhachsan.sqlite");
        Toast.makeText(getApplicationContext(), ma+"", Toast.LENGTH_SHORT).show();

        Cursor cursor = database1.rawQuery("SELECT * FROM hoadon_2409 WHERE ma = ? ",new String[]{ma + ""});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String sophong = cursor.getString(2);
        String dongia = cursor.getString(3);
        String songaylt = cursor.getString(4);
        edtTen.setText(ten);
        edtSoPhong.setText(sophong);
        edtSoNgayLuutru.setText(songaylt);
        edtDonGia.setText(dongia);
    }

   public void sua_khach_hang(){

       Intent intent = getIntent();
       int ma = intent.getIntExtra("ma", -1);
       String ten = edtTen.getText().toString();
       String soPhong = edtSoPhong.getText().toString();
       String donGia = edtDonGia.getText().toString();
       String soNgayLuuTru = edtSoNgayLuutru.getText().toString();
       if(ten.equals("")){
           Toast.makeText(sua_khachhang.this,"Không bỏ trống tên",Toast.LENGTH_SHORT).show();
       }
       else  if(edtSoPhong.equals("")){
           Toast.makeText(sua_khachhang.this,"Không bỏ trống số phòng",Toast.LENGTH_SHORT).show();
       }
       else  if(donGia.equals("")){
           Toast.makeText(sua_khachhang.this,"Không bỏ trống đơn giá",Toast.LENGTH_SHORT).show();
       }
       else  if(soNgayLuuTru.equals("")){
           Toast.makeText(sua_khachhang.this,"Không bỏ trống số lưu trú",Toast.LENGTH_SHORT).show();
       }
       else {
           ContentValues contentValues = new ContentValues();
           contentValues.put("hoten", ten);
           contentValues.put("sophong", soPhong);
           contentValues.put("dongia", donGia);
           contentValues.put("songayluutru", soNgayLuuTru);
           SQLiteDatabase database1 = database.initDatabase(this, "hoadonkhachsan.sqlite");
           database1.update("hoadon_2409", contentValues, "ma = ?", new String[]{ma + ""});
           Toast.makeText(sua_khachhang.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
           Intent intent1 = new Intent(this, MainActivity.class);
           startActivity(intent1);
       }
   }
}