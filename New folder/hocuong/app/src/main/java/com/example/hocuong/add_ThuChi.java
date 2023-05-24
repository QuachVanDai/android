package com.example.hocuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class add_ThuChi extends AppCompatActivity {
    SQLiteDatabase sqlData;
    RadioButton rdlthu,rdlchi;
    Button btnThem, btnQuaylai;
    TextView edtngaythang, edttenkhoan,edtsotien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thu_chi);
        addControls();
        addEvents();
    }
    private void addControls() {
        /* ánh xạ đến ID trong layoput activity_addnhanvien   */
        sqlData = openOrCreateDatabase("thuchi.db",MODE_PRIVATE,null);
        btnThem = (Button) findViewById(R.id.btlthem);
        btnQuaylai = (Button) findViewById(R.id.btlquayve);
        edtngaythang = findViewById(R.id.edtngaythang);
        edtsotien=findViewById(R.id.edtsotien);
        edttenkhoan=findViewById(R.id.edttenkhoan);

        rdlchi = (RadioButton) findViewById(R.id.rblchi);
        rdlthu = (RadioButton) findViewById(R.id.rblthu);

    }

    private void addEvents(){


        /*  sự kiện click nút thêm  */

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        /*  sự kiện click hủy */
        btnQuaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }




    public void checkGTNam(View view){
        // sự kiện khi click nút radioButton Nam
        rdlchi.setChecked(true);
        rdlthu.setChecked(false);
    }
    public void checkGTNu(View view){
        // sự kiện khi click nút radioButton Nu
        rdlchi.setChecked(false);
        rdlthu.setChecked(true);
    }


    /* sự kiện thêm một đối tượng  nhân viên */
    private void insert(){
        /* lấy dữ liệu từ layout từ dòng 139 - > 151 */
        String ngaythang = edtngaythang.getText().toString();
        String tenkhoan = edttenkhoan.getText().toString();
        String sotien=edtsotien.getText().toString();

        ContentValues contentValues = new ContentValues();
        if(rdlthu.isChecked()==true){
            contentValues.put("thu",1 );
            contentValues.put("chi", 0);

        }
        else if(rdlchi.isChecked()==true) {

            contentValues.put("thu",0 );
            contentValues.put("chi", 1);
        }

        contentValues.put("tenKhoan", tenkhoan);
        contentValues.put("ngaythang",ngaythang );
        contentValues.put("sotien", sotien);
        sqlData.insert("tbl_Cac_Khoan", null, contentValues);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
       
    }

    private void cancel(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
