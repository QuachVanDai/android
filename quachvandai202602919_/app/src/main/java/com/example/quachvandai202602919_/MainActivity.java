package com.example.quachvandai202602919_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqlData;
    ArrayList<Vetau> lst_vetau;
    ListView lstview;
    int TongThu=0,SoDu;
    SearchView search;
    TextView txt_ve_tb;
    adapter_vetau adapter_vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createOrOpen();
        addControls();
        readData();
        ve_tb();
        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int dem=0,tongtien=0;
                String khuhoi;
                Vetau  vt = lst_vetau.get(position);
                Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_vetau",null);
                for(int i = 0; i < cursor.getCount(); i++){
                    cursor.moveToPosition(i);

                    String gaden = cursor.getString(2);
                    if(gaden.equals(vt.getGa_den()))
                    {
                        tongtien+=cursor.getInt(3);
                        dem++;
                    }

                }
                if(vt.isKhu_hoi()==true)
                {
                    khuhoi="Khứ hồi";
                }
                else{
                    khuhoi="Một chiều";
                }
     Toast.makeText(MainActivity.this,"co"+dem+" vé "+khuhoi+" đến "+vt.getGa_den()
                     +"\n"+"Giá trung bình "+tongtien/dem
             ,Toast.LENGTH_SHORT).show();
                return false;
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
    void Search(String tien)
    {
        String searchQuery = "SELECT * FROM tbl_vetau WHERE dongia>='" +tien+ "'";
        Cursor cursor = sqlData.rawQuery(searchQuery,null);
        HienDuLieu(cursor);
    }
    public void ve_tb(){
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_vetau",null);
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int intValue = cursor.getInt(5);
            boolean  khuhoi = (intValue!=0);
            if(khuhoi==true){
                int soTien = cursor.getInt(3);
                soTien = soTien *90/100;
                TongThu+=soTien;
            }
            else{
                int soTien = cursor.getInt(3);
                TongThu+=soTien;
            }


        }
        txt_ve_tb.setText((TongThu/cursor.getCount())+"");
    }
    public void createOrOpen(){
        sqlData = openOrCreateDatabase("datavetau.db",MODE_PRIVATE,null);
        try {
     String createTableQuery =
           "CREATE TABLE tbl_vetau(ma INTEGER PRIMARY KEY AUTOINCREMENT,gadi TEXT,gaden TEXT,dongia INT,ngaythang TEXT,khuhoi BOLB)";
            sqlData.execSQL(createTableQuery);
        }catch (Exception e){
            Log.e("loi","Table da ton tai");
        }
       // sqlData.delete("tbl_vetau",null,null);
        /*ContentValues contentValues = new ContentValues();
        contentValues.put("gadi", "Vinh");
        contentValues.put("gaden","Nam Dinh" );
        contentValues.put("dongia", "100000");
        contentValues.put("ngaythang","11/09/2022");
        contentValues.put("khuhoi", 0);
        sqlData.insert("tbl_vetau", null, contentValues);

        contentValues.put("gadi", "Vinh");
        contentValues.put("gaden","Nam Dinh" );
        contentValues.put("dongia", "180000");
        contentValues.put("ngaythang","12/10/2022");
        contentValues.put("khuhoi", 1);
        sqlData.insert("tbl_vetau", null, contentValues);

        contentValues.put("gadi", "Ha Noi");
        contentValues.put("gaden","Vinh" );
        contentValues.put("dongia", "200000");
        contentValues.put("ngaythang","14/09/2022");
        contentValues.put("khuhoi", 0);
        sqlData.insert("tbl_vetau", null, contentValues);

        contentValues.put("gadi", "Thanh Hoa");
        contentValues.put("gaden","Nam Dinh" );
        contentValues.put("dongia", "220000");
        contentValues.put("ngaythang","1/09/2022");
        contentValues.put("khuhoi", 1);
        sqlData.insert("tbl_vetau", null, contentValues);

        contentValues.put("gadi", "Ha Noi");
        contentValues.put("gaden","Vinh" );
        contentValues.put("dongia", "360000");
        contentValues.put("ngaythang","11/09/2022");
        contentValues.put("khuhoi", 1);
        sqlData.insert("tbl_vetau", null, contentValues);*/
    }
    private  void addControls() {
        search = findViewById(R.id.searchView);
        lstview = findViewById(R.id.lstview);
        lst_vetau = new ArrayList<>();
        adapter_vt = new adapter_vetau(this,lst_vetau);
        lstview.setAdapter(adapter_vt);
        txt_ve_tb = findViewById(R.id.txt_ve_tb);
    }

    private void readData(){
        String orderBy = "dongia ASC";
        // SQLiteDatabase database1 = database.initDatabase(this,"hoadonkhachsan3.sqlite");
        // db.delete("hoadon_2409", null, null);
        Cursor cursor1 = sqlData.query("tbl_vetau",
                null, null, null, null, null, orderBy);
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_vetau",null);
        HienDuLieu(cursor1);
    }
    public void HienDuLieu(Cursor cursor){
        lst_vetau.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String gadi = cursor.getString(1);
            String gaden = cursor.getString(2);
            int soTien = cursor.getInt(3);
            String ngayThang = cursor.getString(4);
            int intValue = cursor.getInt(5);
            boolean  khuhoi = (intValue!=0);
            lst_vetau.add(new Vetau(ma, gadi,gaden, soTien,  ngayThang,khuhoi));
        }
        adapter_vt.notifyDataSetChanged();
    }
}