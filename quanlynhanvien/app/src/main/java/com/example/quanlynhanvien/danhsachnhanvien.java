package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class danhsachnhanvien extends AppCompatActivity {
    final String DATABASE_NAME = "qlnhanvien5.sqlite";
    SQLiteDatabase database1;
    ListView lstview;
    SearchView search;
    String sort = "Ten ASC";
    ArrayList<nhanvien> lstnhanvien;
    adapternhanvien adapternv;
    ImageView imgadd,imgback,imgsort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachnhanvien);
        addControls();
        addEvents();
        readData();
    }
    private void addEvents() {
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(danhsachnhanvien.this,addnhanvien.class);
                startActivity(intent);
            }
        });
            imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(danhsachnhanvien.this,ManHinhChinh.class);
                startActivity(intent);
            }
            });
            imgsort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sortName(sort);
                    if(sort.equals("Ten ASC")){
                        sort="Ten DESC";
                    }
                    else if(sort.equals("Ten DESC")){
                        sort="Ten ASC";
                    }
                }
            });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Xử lý tìm kiếm khi người dùng ấn Enter hoặc nút Search
               // Search(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Xử lý tìm kiếm khi người dùng thay đổi văn bản trong ô tìm kiếm
                Search(newText);
                return false;
            }
        });

    }
public void sortName(String orderBy){
    SQLiteDatabase database1 = database.initDatabase(this, DATABASE_NAME);
   // String searchQuery = "SELECT * FROM nhanvien5 WHERE Ten LIKE '%" + query+ "%'";
  //  Cursor cursor = database1.Query(searchQuery,null);
    Cursor cursor = database1.query("nhanvien5",
            null, null, null, null, null, orderBy);
    lstnhanvien.clear();
    for(int i = 0; i < cursor.getCount(); i++){
        cursor.moveToPosition(i);
        int id = cursor.getInt(0);
        String ten = cursor.getString(1);
        String chucvu = cursor.getString(5);
        byte[] anh = cursor.getBlob(8);
        lstnhanvien.add(new nhanvien(id, ten, chucvu, anh));
    }
    adapternv.notifyDataSetChanged();
}
    public void Search(String query){

        SQLiteDatabase database1 = database.initDatabase(this, DATABASE_NAME);
        String searchQuery = "SELECT * FROM nhanvien5 WHERE Ten LIKE '%" + query+ "%'";
        Cursor cursor = database1.rawQuery(searchQuery,null);
        lstnhanvien.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String chucvu = cursor.getString(5);
            byte[] anh = cursor.getBlob(8);
            lstnhanvien.add(new nhanvien(id, ten, chucvu, anh));
        }
        adapternv.notifyDataSetChanged();
    }
    private  void addControls(){
        lstview = findViewById(R.id.listView);
        imgadd = findViewById(R.id.imgadd);
        imgback = findViewById(R.id.imgback);
        lstnhanvien = new ArrayList<>();
        adapternv = new adapternhanvien(this,lstnhanvien);
        lstview.setAdapter(adapternv);
        imgsort = findViewById(R.id.imgsort);
        search = findViewById(R.id.txtSreach);
    }
    private void readData(){
        this.database1 = database.initDatabase(this, DATABASE_NAME);
      // SQLiteDatabase db = database.initDatabase(this, DATABASE_NAME);
      // db.delete("nhanvien2", null, null);
//        String sql = "ALTER TABLE nhanvien2 DROP SDT1";
//        db.execSQL(sql);
//        ALTER TABLE table_name
//        ALTER COLUMN column_name datatype
        Cursor cursor = database1.rawQuery("SELECT * FROM nhanvien5",null);
        lstnhanvien.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String chucvu = cursor.getString(5);
            byte[] anh = cursor.getBlob(8);
            lstnhanvien.add(new nhanvien(id, ten, chucvu, anh));
        }
        adapternv.notifyDataSetChanged();
    }

}