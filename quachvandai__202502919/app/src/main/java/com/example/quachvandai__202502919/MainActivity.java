package com.example.quachvandai__202502919;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqlData;
    ArrayList<giaodich> lst_giaodich;
    ListView lstview;
    int TongThu = 0, TongChi;
    SearchView search;
    TextView txt_SoDu;
    adapter_giaodich adapter_gd;
    int index=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        createOrOpen();
        readData();
        sodu();
        registerForContextMenu(lstview);
        lstview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index=position;
                return false;
            }
        });
    }
    public void sodu(){
        Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Giao_Dich",null);
        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int intValue = cursor.getInt(2);
            boolean  loaiGD = (intValue!=0);
            if(loaiGD==true){
                int soTien=cursor.getInt(4);
                TongThu+=soTien;
            }
            else if(loaiGD==false){
                int soTien=cursor.getInt(4);
                TongChi+=soTien;
            }

        }
        txt_SoDu.setText((TongThu-TongChi)+"");
    }
    public void delete(){
        giaodich gd1 = lst_giaodich.get(index);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage(
                    "Bạn có muốn xóa khỏi khoản giao dịch này không?\n"
                            +"Ngày tháng : "+gd1.getNgay_thang()+"\n"
                            +"Tiền đến từ: "+gd1.getTen()+"\n"
                            +"Tiền đi tới: "+gd1.getTen() +"\n"
                            +"Số tiền "+gd1.getSo_tien()+"\n");


            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    giaodich gd = lst_giaodich.get(index);
                    sqlData.delete("tbl_Giao_Dich", "ma = ?", new String[]{gd.getMa() + ""});
                    index = -1;
                    Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Giao_Dich",null);
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



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.item_sua:
                delete();
                // Xử lý khi mục menu Item 1 được chọn
                return true;
            case R.id.item_xoa:
                // Xử lý khi mục menu Item 2 được chọn
                delete();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);


    }

    private void addControls () {
        search = findViewById(R.id.searchView);
        lstview = findViewById(R.id.lstview);
        lst_giaodich = new ArrayList<>();
        adapter_gd = new adapter_giaodich(this, lst_giaodich);
        lstview.setAdapter(adapter_gd);
        txt_SoDu = findViewById(R.id.txt_sodu);
    }
    public void createOrOpen() {
        sqlData = openOrCreateDatabase("datagiaodich.db", MODE_PRIVATE, null);
        try {
            String createTableQuery = "CREATE TABLE tbl_Giao_Dich(ma INTEGER PRIMARY KEY AUTOINCREMENT,ten TEXT,loaigiaodich BOLB,noidung TEXT,sotien INT,ngaythang TEXT)";
            sqlData.execSQL(createTableQuery);
        } catch (Exception e) {
            Log.e("loi", "Table da ton tai");
        }
       /*ContentValues contentValues = new ContentValues();
        contentValues.put("ten", "Dai1");
        contentValues.put("loaigiaodich",1 );
        contentValues.put("noidung", "Thưởng");
        contentValues.put("sotien",1000000);
        contentValues.put("ngaythang", "17/09/2023");
        sqlData.insert("tbl_Giao_Dich", null, contentValues);*/
    }
        private void readData ()
        {
           // String orderBy = "sotien DESC";
            // SQLiteDatabase database1 = database.initDatabase(this,"hoadonkhachsan3.sqlite");
            // db.delete("hoadon_2409", null, null);
        /*    Cursor cursor1 = sqlData.query("tbl_Cac_Khoan",
                    null, null, null, null, null, orderBy);*/
            Cursor cursor = sqlData.rawQuery("SELECT * FROM tbl_Giao_Dich",null);
            HienDuLieu(cursor);
        }
        public void HienDuLieu (Cursor cursor){
            lst_giaodich.clear();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                int ma = cursor.getInt(0);
                String ten = cursor.getString(1);
                int intValue_loaiGiaoDich = cursor.getInt(2);
                boolean loaiGiaoDich  = (intValue_loaiGiaoDich != 0);
                String noiDung = cursor.getString(3);
                int soTien = cursor.getInt(4);
                String ngayThang = cursor.getString(5);
                lst_giaodich.add(new giaodich(ma, ten, loaiGiaoDich,noiDung, soTien, ngayThang));
            }
            adapter_gd.notifyDataSetChanged();
        }

    }