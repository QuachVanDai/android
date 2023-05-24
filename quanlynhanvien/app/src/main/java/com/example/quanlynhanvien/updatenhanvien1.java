package com.example.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class updatenhanvien1 extends AppCompatActivity {

    final String DATABASE_NAME = "qlnhanvien5.sqlite";
    final int RESQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    int id = -1;

    Button btnChonHinh, btnChupHinh, btnLuu, btnHuy;
    EditText edtTen, edtSdt, edtChucVu,edtGhiChu,edtDiaChi,edtLuong,edtSoNgayLam;
    ImageView imgHinhDaiDien;
    RadioButton rdoNam,rdoNu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenhanvien1);
        addControls();
        addEvents();
        initUI();
    }

    private void addControls() {
        btnChonHinh = (Button) findViewById(R.id.btnChonHinh1);
        btnChupHinh = (Button) findViewById(R.id.btnChupHinh1);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        edtTen = (EditText) findViewById(R.id.edtTen1);
        edtSdt = (EditText) findViewById(R.id.edtSdt1);
        edtChucVu = (EditText) findViewById(R.id.editChucVu1);
        edtSoNgayLam = (EditText) findViewById(R.id.editSoNgayLam1);
        edtLuong = (EditText) findViewById(R.id.editLuong1);
        edtDiaChi = (EditText) findViewById(R.id.editDiaChi1);
        edtGhiChu = (EditText) findViewById(R.id.editGhiChu1);
        rdoNam = (RadioButton) findViewById(R.id.rdoNam1);
        rdoNu = (RadioButton) findViewById(R.id.rdoNu1);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien1);
    }

    public void checkGTNam1(View view){
        // sự kiện khi click nút radioButton Nam
        rdoNam.setChecked(true);
        rdoNu.setChecked(false);
    }
    public void checkGTNu1(View view){
        // sự kiện khi click nút radioButton Nu
        rdoNam.setChecked(false);
        rdoNu.setChecked(true);
    }
    private void addEvents(){
        btnChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btnChupHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update1();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
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
        int  songaylam= cursor.getInt(7);
        byte[] anh = cursor.getBlob(8);
        String ghiChu = cursor.getString(9);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imgHinhDaiDien.setImageBitmap(bitmap);
        edtTen.setText(ten);
        if(gioiTinh.equals("Nam")) {rdoNam.setChecked(true); rdoNu.setChecked(false);}
        else if(gioiTinh.equals("Nu")) {rdoNu.setChecked(false); rdoNu.setChecked(true);}
        edtDiaChi.setText(DiaChi);
        edtSdt.setText(sdt);
        edtChucVu.setText(chucVu);
        edtLuong.setText( String.valueOf(luong));
        edtSoNgayLam.setText(String.valueOf(songaylam));
        edtGhiChu.setText(ghiChu);
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
            } else if (requestCode == RESQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
        }
    }

    public void update1(){
        String ten = edtTen.getText().toString();
        String sdt = edtSdt.getText().toString();
        String chucVu = edtChucVu.getText().toString();
        String gioiTinh = "";
        if(rdoNam.isChecked()==true){
            gioiTinh="Nam";
        }
        else if(rdoNu.isChecked()==true) gioiTinh="Nu";
        String DiaChi = edtDiaChi.getText().toString();
        String ghiChu = edtGhiChu.getText().toString();
        String luong = edtLuong.getText().toString();
        String songaylam = edtSoNgayLam.getText().toString();
        byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", ten);
        contentValues.put("SDT", sdt);
        contentValues.put("DiaChi", DiaChi);
        contentValues.put("GhiChu", ghiChu);
        contentValues.put("ChucVu", chucVu);
        contentValues.put("Anh", anh);
        contentValues.put("Luong",luong);
        contentValues.put("SoNgayLam",songaylam);
        SQLiteDatabase database2 = database.initDatabase(this, "qlnhanvien5.sqlite");
        database2.update("nhanvien5", contentValues, "ID = ?", new String[] {id + ""});
        Intent intent = new Intent(this, danhsachnhanvien.class);
        startActivity(intent);
    }

    private void cancel(){
        Intent intent = new Intent(this, danhsachnhanvien.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}