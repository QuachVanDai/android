package com.example.quachvandai_04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqlData;
    ListView lstview;
    SearchView search;
    String sort = "sophong ASC";
    ArrayList<hoadon_2409> lsthoadon;
    Adapter_04 adapter_04;
    Button btn_sapXep,btn_ThemMoi,btn_Xoa,btn_Sua;
    int index=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        createOrOpen();
        readData();
        addEvents();
    }
    public void createOrOpen(){
       sqlData = openOrCreateDatabase("hoadonkhachsan6.db",MODE_PRIVATE,null);
       try {
           String createTableQuery = "CREATE TABLE hoadon_9 (ma INTEGER PRIMARY KEY AUTOINCREMENT,ten TEXT,sophong INT,dongia INT,songayluutru INT)";
           sqlData.execSQL(createTableQuery);
       }catch (Exception e){
           Log.e("loi","Table da ton tai");
       }
    }
    private  void addControls() {
        lstview = findViewById(R.id.lstView);
        lsthoadon = new ArrayList<>();
        adapter_04 = new Adapter_04(this,lsthoadon);
        lstview.setAdapter(adapter_04);
        search = findViewById(R.id.search);
        btn_sapXep = findViewById((R.id.sx_sophong));
        btn_ThemMoi = findViewById((R.id.btn_Them_Moi_KH));
        btn_Xoa = findViewById((R.id.btn_Xoa));
        btn_Sua = findViewById((R.id.btn_Sua));
    }
    private void readData(){
       // SQLiteDatabase database1 = database.initDatabase(this,"hoadonkhachsan3.sqlite");
     // db.delete("hoadon_2409", null, null);
        Cursor cursor = sqlData.rawQuery("SELECT * FROM hoadon_9",null);
        HienDuLieu(cursor);
    }

    public void sort_SoPhong(String orderBy){
        //SQLiteDatabase database1 = database.initDatabase(this,"hoadonkhachsan3.sqlite");
        Cursor cursor = sqlData.query("hoadon_1",
                null, null, null, null, null, orderBy);
        HienDuLieu(cursor);
    }
    private void addEvents(){
        btn_sapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_SoPhong(sort);
                if(sort.equals("sophong ASC")){
                    sort="sophong DESC";
                }
                else if(sort.equals("sophong DESC")){
                    sort="sophong ASC";
                }
            }
        });
        btn_ThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, them_khachhang.class);
                startActivity(intent);
            }
        });
        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=-1) {
                    Intent intent = new Intent(MainActivity.this, sua_khachhang.class);
                    hoadon_2409 hd = lsthoadon.get(index);
                    intent.putExtra("ma", hd.getMa());
                    index=-1;
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Hãy chọn một khách hàng cần sửa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SQLiteDatabase database2 = database.initDatabase(this,"hoadonkhachsan3.sqlite" );
        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=-1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setIcon(android.R.drawable.ic_delete);
                    builder.setTitle("Xác nhận xóa");
                    builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hoadon_2409 hd = lsthoadon.get(index);
                            database2.delete("hoadon_1", "ma = ?", new String[]{hd.getMa() + ""});
                            index = -1;
                            Cursor cursor = sqlData.rawQuery("SELECT * FROM hoadon_2409",null);
                            HienDuLieu(cursor);
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Hãy chọn một khách hàng cần xóa", Toast.LENGTH_SHORT).show();

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
                if(newText.equals("")){
                    Search("0");
                }
                else
                {
                    Search(newText);
                }
                return false;
            }
        });
        SQLiteDatabase database1 = database.initDatabase(this, "hoadonkhachsan3.sqlite");
        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
                Toast.makeText(getApplicationContext(), index+"", Toast.LENGTH_SHORT).show();
            }
        });
        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                hoadon_2409 hd = lsthoadon.get(position);

                String searchQuery = "SELECT * FROM hoadon_1 " +
                        "WHERE (dongia) >=('" + hd.getDonGia() * hd.getSoNgayLuuTru()+ "')/songayluutru";
                String acountQuery = "SELECT count(*) from hoadon_2409 where (dongia) >=('" + hd.getDonGia() * hd.getSoNgayLuuTru()+ "')/songayluutru";
                Cursor cursor1 = database1.rawQuery(searchQuery,null);
                Cursor cursor2 = database1.rawQuery(acountQuery,null);
                int count = 0;
                if (cursor2.moveToFirst()) {
                    count = cursor2.getInt(0);
                }
                HienDuLieu(cursor1);
                Toast.makeText(getApplicationContext(), hd.getHoTen() +" có "+ count+" hóa đơn >="+hd.getDonGia() * hd.getSoNgayLuuTru(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void Search(String query) {
        SQLiteDatabase database1 = database.initDatabase(this, "hoadonkhachsan3.sqlite");
        String searchQuery = "SELECT * FROM hoadon_1 WHERE (dongia) >=('" + query+ "'/songayluutru)";
        Cursor cursor = database1.rawQuery(searchQuery,null);
       HienDuLieu(cursor);
    }
    public void HienDuLieu(Cursor cursor){
        lsthoadon.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            int sophong = cursor.getInt(2);
            int DonGia = cursor.getInt(3);
            int SoNgayLuuTru = cursor.getInt(4);
            lsthoadon.add(new hoadon_2409(ma, ten, sophong, DonGia , SoNgayLuuTru));
        }
        adapter_04.notifyDataSetChanged();
    }
}