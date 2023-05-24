package com.example.quachvandai_202602919;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boardcastReceiver boardcastReceiver;
    SQLiteDatabase sqlData;
    ArrayList<thu_phi> lst_thuphi;
    ListView lstview;
    int TongThu=0,SoDu;
    SearchView search;
    TextView txt_TongThu,txt_SoDu;
    adapter_khoan_thu adapter_kt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        createOrOpen();
        readData();
        tongthu();
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
        Toast.makeText(this,lst_thuphi.size()+"",Toast.LENGTH_SHORT).show();
        boardcastReceiver = new boardcastReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intent = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(boardcastReceiver,intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(boardcastReceiver);
    }

    public void Search(String text){

        String searchQuery = "SELECT * FROM tbl_Cac_Khoan WHERE tenkhoan LIKE '%" + text+ "%'";
        Cursor cursor = sqlData.rawQuery(searchQuery,null);
        HienDuLieu(cursor);
    }
    public void tongthu(){
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Cac_Khoan",null);
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int intValue_khoanThu = cursor.getInt(4);
            boolean  khoanThu = (intValue_khoanThu!=0);
            if(khoanThu==true){
                int soTien=cursor.getInt(3);
                TongThu+=soTien;
            }
            else{
                int soTien=cursor.getInt(3);
                SoDu=TongThu-soTien;
            }

        }
        txt_TongThu.setText("Tổng thu: "+TongThu+"");
        txt_SoDu.setText(SoDu+"");
    }
    private  void addControls() {
        search = findViewById(R.id.searchView);
        lstview = findViewById(R.id.lstview);
        lst_thuphi = new ArrayList<>();
        adapter_kt = new adapter_khoan_thu(this,lst_thuphi);
        lstview.setAdapter(adapter_kt);
        txt_TongThu = findViewById(R.id.txt_tongThu);
        txt_SoDu = findViewById(R.id.txt_sodu);
    }
    public void createOrOpen(){
        sqlData = openOrCreateDatabase("khoanthu.db",MODE_PRIVATE,null);
        try {
 String createTableQuery = "CREATE TABLE tbl_Cac_Khoan(ma INTEGER PRIMARY KEY AUTOINCREMENT,tenKhoan TEXT,ngaythang TEXT,sotien INT, khoanthu BOLB,khoanchi BOLB)";
            sqlData.execSQL(createTableQuery);
        }catch (Exception e){
            Log.e("loi","Table da ton tai");
        }
    }
    private void readData(){
        String orderBy = "sotien DESC";
        // SQLiteDatabase database1 = database.initDatabase(this,"hoadonkhachsan3.sqlite");
        // db.delete("hoadon_2409", null, null);
        Cursor cursor1 = sqlData.query("tbl_Cac_Khoan",
                null, null, null, null, null, orderBy);
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Cac_Khoan",null);
        HienDuLieu(cursor1);
    }
    public void HienDuLieu(Cursor cursor){
        lst_thuphi.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String tenKhoan = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            int soTien = cursor.getInt(3);
            int intValue_khoanThu = cursor.getInt(4);
            boolean  khoanThu = (intValue_khoanThu!=0);
            int intValue_khoanChi = cursor.getInt(5);
            boolean  khoanChi = (intValue_khoanChi!=0);
            lst_thuphi.add(new thu_phi(ma, tenKhoan, ngayThang, soTien, khoanThu,khoanChi));
        }
        adapter_kt.notifyDataSetChanged();
    }
}
/*ContentValues contentValues = new ContentValues();
        contentValues.put("tenkhoan", "mua quần áo");
        contentValues.put("ngaythang","14/09/2023" );
        contentValues.put("sotien", "50000");
        contentValues.put("khoanthu",0);
        contentValues.put("khoanchi", 1);
        sqlData.insert("tbl_Cac_Khoan", null, contentValues);
        contentValues.put("tenkhoan", "luong co ban");
        contentValues.put("ngaythang","23/09/2023" );
        contentValues.put("sotien", "30000");
        contentValues.put("khoanthu",1);
        contentValues.put("khoanchi", 0);
        sqlData.insert("tbl_Cac_Khoan", null, contentValues);
        contentValues.put("tenkhoan", "Ăn tối");
        contentValues.put("ngaythang","1/09/2023" );
        contentValues.put("sotien", "30000");
        contentValues.put("khoanthu",0);
        contentValues.put("khoanchi", 1);
        sqlData.insert("tbl_Cac_Khoan", null, contentValues);*/