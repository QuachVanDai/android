package com.example.quanlynhanvien;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhanvien.R;

import java.util.ArrayList;

public class adapternhanvien extends BaseAdapter {
    Activity context;
    ArrayList<nhanvien> list;
    //private LayoutInflater inflater;
    int id1;
    public adapternhanvien(Activity context, ArrayList<nhanvien> list) {
        this.context = context;
        this.list = list;
      //  this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       View v = convertView;
        if(v == null)
            v = inflater.inflate(R.layout.listviewnhanvien, null);
        ImageView imgAvatar = (ImageView)v.findViewById(R.id.imgavatar);
        TextView txtID = ( TextView) v.findViewById(R.id.txtid);
        TextView txtTen = ( TextView) v.findViewById(R.id.txtTen);
        TextView txtChucVu = ( TextView) v.findViewById(R.id.txtChucVu);
        nhanvien nhanvien = list.get(position);
        txtID.setText(nhanvien.getId() + "");
        txtTen.setText(nhanvien.getTen() );
        txtChucVu.setText(nhanvien.getChucVu());
        Bitmap bmAvatar = BitmapFactory.decodeByteArray(nhanvien.getAnh(), 0,nhanvien.getAnh().length);
        imgAvatar.setImageBitmap(bmAvatar);

        Button btnSua = (Button) v.findViewById(R.id.btnSua);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updatenhanvien1.class);
                intent.putExtra("ID", nhanvien.getId());
                context.startActivity(intent);
            }
        });
        Button btnInfo = (Button) v.findViewById(R.id.btninfo);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Info.class);
                intent.putExtra("ID", nhanvien.getId());
                context.startActivity(intent);
            }
        });
        Button btnXoa = (Button) v.findViewById(R.id.btnXoa1);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(nhanvien.getId());
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
        });
        CheckBox ckChamCong = v.findViewById(R.id.ckchamcong);
        ckChamCong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ckChamCong.isChecked()==false){
                    SQLiteDatabase database2 = database.initDatabase(context, "qlnhanvien5.sqlite");
                    Cursor cursor = database2.rawQuery("SELECT * FROM nhanvien5 WHERE ID = ? ",
                            new String[]{nhanvien.getId() + ""});
                    cursor.moveToFirst();
                    ContentValues contentValues = new ContentValues();
                    int  songaylam= cursor.getInt(7);
                    contentValues.put("SoNgayLam",songaylam-1);
                    database2.update("nhanvien5", contentValues,
                            "ID = ?", new String[] {nhanvien.getId() + ""});
                }
                else{
                    SQLiteDatabase database2 = database.initDatabase(context, "qlnhanvien5.sqlite");
                    Cursor cursor = database2.rawQuery("SELECT * FROM nhanvien5 WHERE ID = ? ",
                            new String[]{nhanvien.getId() + ""});
                    cursor.moveToFirst();
                    ContentValues contentValues = new ContentValues();
                    int  songaylam= cursor.getInt(7);
                    contentValues.put("SoNgayLam",songaylam+1);
                    database2.update("nhanvien5", contentValues,
                            "ID = ?", new String[] {nhanvien.getId() + ""});
                }

            }
        });
       return v;
    }
    private void delete(int idNhanVien) {
        SQLiteDatabase database1 = database.initDatabase(context,"qlnhanvien5.sqlite");
        database1.delete("nhanvien5", "ID = ?", new String[]{idNhanVien + ""});
        list.clear();
        Cursor cursor = database1.rawQuery("SELECT * FROM nhanvien5", null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String chucvu = cursor.getString(5);
            byte[] anh = cursor.getBlob(8);
            list.add(new nhanvien(id, ten, chucvu, anh));
        }
        notifyDataSetChanged();
    }
}
