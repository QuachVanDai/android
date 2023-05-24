package com.example.quanlynhanvien;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
public class addnhanvien extends AppCompatActivity {

    final String DATABASE_NAME = "qlnhanvien5.sqlite";
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    RadioButton rdoNam,rdoNu;
    Button btnChonHinh, btnChupHinh, btnThem, btnHuy;
    EditText edtTen, edtSdt, edtChucVu,edtGhiChu,edtDiaChi,edtLuong,edtSoNgayLam;
    ImageView imgHinhDaiDien=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnhanvien);
        addControls();
        addEvents();
    }
    private void addControls() {
        /* ánh xạ đến ID trong layoput activity_addnhanvien   */
        btnChonHinh = (Button) findViewById(R.id.btnChonHinh);
        btnChupHinh = (Button) findViewById(R.id.btnChupHinh);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtChucVu = (EditText) findViewById(R.id.editChucVu);
        edtDiaChi = (EditText) findViewById(R.id.editDiaChi);
        edtGhiChu = (EditText) findViewById(R.id.editGhiChu);
        edtLuong = (EditText) findViewById(R.id.editLuongCoBan);
        edtSoNgayLam = (EditText) findViewById(R.id.editSoNgayLam);
        edtSdt = (EditText) findViewById(R.id.edtSdt);
        rdoNam = (RadioButton) findViewById(R.id.rdoNam);
        rdoNu = (RadioButton) findViewById(R.id.rdoNu);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
    }

    private void addEvents(){
        /*  sự kiện click chọn ảnh */
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        /*  sự kiện click chụp hình */

        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
        /*  sự kiện click nút thêm  */

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        /*  sự kiện click hủy */
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESQUEST_TAKE_PHOTO);
    }
    private void choosePhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* sự kiện  lấy ảnh từ thiết bị*/
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinhDaiDien.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }         /* sự kiện  lấy ảnh từ camera */
            else if (requestCode == RESQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
        }
    }
    public void checkGTNam(View view){
        // sự kiện khi click nút radioButton Nam
        rdoNam.setChecked(true);
        rdoNu.setChecked(false);
    }
    public void checkGTNu(View view){
        // sự kiện khi click nút radioButton Nu
        rdoNam.setChecked(false);
        rdoNu.setChecked(true);
    }
    /* sự kiện thêm một đối tượng  nhân viên */
    private void insert(){
        /* lấy dữ liệu từ layout từ dòng 139 - > 151 */
        String ten = edtTen.getText().toString();
        String sdt = edtSdt.getText().toString();
        String gioiTinh="";
        if(rdoNam.isChecked()==true){
            gioiTinh="Nam";
        }
        else if(rdoNu.isChecked()==true) gioiTinh="Nu";
        String chucVu=edtChucVu.getText().toString();
        String ghiChu=edtGhiChu.getText().toString();
        String diaChi=edtDiaChi.getText().toString() ;
        String luong =edtLuong.getText().toString() ;
        String songaylam=edtSoNgayLam.getText().toString() ;
        /* kiểm tra các dữ liệu */
        if(ten.equals("")){
           // edtTen.setBackground(ContextCompat.getDrawable(this, R.color.purple_200));
            Toast.makeText(addnhanvien.this,"Không bỏ trống tên",Toast.LENGTH_SHORT).show();
            edtTen.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(gioiTinh.equals("")){
            rdoNam.setTextColor(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            rdoNu.setTextColor(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
            Toast.makeText(addnhanvien.this,"Chọn giới tính",Toast.LENGTH_SHORT).show();
        }
        else  if(chucVu.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống chức vụ",Toast.LENGTH_SHORT).show();
            edtChucVu.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(sdt.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống số điện thoại",Toast.LENGTH_SHORT).show();
            edtSdt.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else if( sdt.length()!=9){
            Toast.makeText(addnhanvien.this,"Độ dài khác 9 chữ số",Toast.LENGTH_SHORT).show();
            edtSdt.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(diaChi.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống địa chỉ",Toast.LENGTH_SHORT).show();
            edtDiaChi.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(songaylam.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống ngày làm",Toast.LENGTH_SHORT).show();
            edtSoNgayLam.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(luong.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống lương",Toast.LENGTH_SHORT).show();
            edtLuong.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else  if(ghiChu.equals("")){
            Toast.makeText(addnhanvien.this,"Không bỏ trống ghi chú",Toast.LENGTH_SHORT).show();
            edtGhiChu.setBackgroundTintList(ContextCompat.getColorStateList(this, com.google.android.material.R.color.design_default_color_error));
        }
        else{
            try {
                byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
                ContentValues contentValues = new ContentValues();
                contentValues.put("Ten", ten);
                contentValues.put("GioiTinh", gioiTinh);
                contentValues.put("DiaChi", diaChi);
                contentValues.put("SDT", sdt);
                contentValues.put("ChucVu", chucVu);
                contentValues.put("Luong", luong);
                contentValues.put("SoNgayLam", songaylam);
                contentValues.put("GhiChu", ghiChu);
                contentValues.put("Anh", anh);
                /* insert dữ liệu vào bảng nhanvien5 */
                SQLiteDatabase database1 = database.initDatabase(this, "qlnhanvien5.sqlite");
                database1.insert("nhanvien5",null, contentValues);
                Intent intent = new Intent(this, danhsachnhanvien.class);
                startActivity(intent);
            }catch (Exception c){
                choosePhoto();
            }

        }
        /* gán dữ liệu thông qua ContentValues  các key phải trùng với key trong
         table nhanvien5 trong csdl qlnhanvien5.sqlite
         */

    }

    private void cancel(){
        Intent intent = new Intent(this, danhsachnhanvien.class);
        startActivity(intent);
    }
// hàm sử lý ảnh
    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}