package com.example.hocuong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     SQLiteDatabase sqlData;
     int TongThu=0,TongChi=0;
    ListView listView;
    ArrayList<ThuChi> list;
    SearchView search;
    AdapterThuChi adapter;
    TextView txtSo_Du;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createOrOpen();
        addControls();
        readData();
        sodu();
        Toast.makeText(this,list.size()+"",Toast.LENGTH_SHORT).show();
    }
    public void sodu(){
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Cac_Khoan",null);
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int intValue = cursor.getInt(4);
            boolean  thu = (intValue!=0);
            if(thu==true){
                int soTien=cursor.getInt(3);
                TongThu+=soTien;
            }
            else {
                int soTien=cursor.getInt(3);
                TongChi+=soTien;
            }

        }
        txtSo_Du.setText("Số Dư:       "+(TongThu-TongChi)+"");
    }
    public void createOrOpen(){
        sqlData = openOrCreateDatabase("thuchi.db",MODE_PRIVATE,null);
        try {
            String createTableQuery = "CREATE TABLE tbl_Cac_Khoan(ma INTEGER PRIMARY KEY AUTOINCREMENT,tenKhoan TEXT,ngaythang TEXT,sotien INT, thu BOLB,chi BOLB)";
            sqlData.execSQL(createTableQuery);
        }catch (Exception e){

        }
        /*ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan", "Lương");
        contentValues.put("ngaythang","13/09/2023" );
        contentValues.put("sotien", "10000");
        contentValues.put("thu",1 );
        contentValues.put("chi", 0);
        sqlData.insert("tbl_Cac_Khoan", null, contentValues);*/
    }
    private void readData() {
        String createTableQuery = "CREATE TABLE tbl_Cac_Khoan(ma INTEGER PRIMARY KEY AUTOINCREMENT,tenKhoan TEXT,ngaythang TEXT,sotien INT, thu BOLB,chi BOLB)";

      //  String searchQuery = "SELECT * FROM HoaDon_10 WHERE Phong ";
        Cursor cursor1 = sqlData.query("tbl_Cac_Khoan",
                null,"thu=1",null,null,null,"sotien DESC");
        Cursor cursor2 = sqlData.query("tbl_Cac_Khoan",
                null,"chi=1",null,null,null,"sotien DESC");
       // Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Cac_Khoan",null);
        list.clear();
        for(int i = 0; i < cursor1.getCount(); i++){
            cursor1.moveToPosition(i);
            int ma = cursor1.getInt(0);
            String tenKhoan = cursor1.getString(1);
            String ngayThang = cursor1.getString(2);
            int soTien = cursor1.getInt(3);
            int intValue_khoanThu = cursor1.getInt(4);
            boolean  khoanThu = (intValue_khoanThu!=0);
            int intValue_khoanChi = cursor1.getInt(5);
            boolean  khoanChi = (intValue_khoanChi!=0);
            list.add(new ThuChi(ma, tenKhoan, ngayThang, soTien, khoanThu,khoanChi));
        }
        for(int i = 0; i < cursor2.getCount(); i++){
            cursor2.moveToPosition(i);
            int ma = cursor2.getInt(0);
            String tenKhoan = cursor2.getString(1);
            String ngayThang = cursor2.getString(2);
            int soTien = cursor2.getInt(3);
            int intValue_khoanThu = cursor2.getInt(4);
            boolean  khoanThu = (intValue_khoanThu!=0);
            int intValue_khoanChi = cursor2.getInt(5);
            boolean  khoanChi = (intValue_khoanChi!=0);
            list.add(new ThuChi(ma, tenKhoan, ngayThang, soTien, khoanThu,khoanChi));
        }
        adapter.notifyDataSetChanged();

    }
    private void addControls() {
        btnAdd= (Button) findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_ThuChi.class);
                startActivity(intent);
            }

        });
        txtSo_Du = findViewById(R.id.txtdu);
        listView = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new AdapterThuChi(this,list);
        listView.setAdapter(adapter);
//        search = findViewById(R.id.search);
    }
}