package com.example.quachvandai__202502919;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_giaodich extends BaseAdapter {
    Activity context;
    ArrayList<giaodich> lstGiaoDich;

    public adapter_giaodich(Activity context, ArrayList<giaodich> lstGiaoDich) {
        this.context = context;
        this.lstGiaoDich = lstGiaoDich;
    }

    @Override
    public int getCount() {
        return lstGiaoDich.size();
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
        if(v == null){}
        v = inflater.inflate(R.layout.item, null);
        TextView txtTen= ( TextView) v.findViewById(R.id.txt_ten);
        TextView txtNoiDung = ( TextView) v.findViewById(R.id.txt_noidung);
        TextView txtSoTien = ( TextView) v.findViewById(R.id.txt_soTien);
        TextView txtNgayThang= ( TextView) v.findViewById(R.id.txt_ngaythang);
        giaodich gd = lstGiaoDich.get(position);
        if(gd.isLoai_giao_dich()==true)
        {
            txtTen.setText("Tiền đến từ "+gd.getTen()+": ");
            txtNoiDung.setText(gd.getNoi_dung() );
            txtTen.setBackgroundColor(Color.RED);
            txtNgayThang.setText(gd.getNgay_thang());
            txtNgayThang.setBackgroundColor(Color.RED);
            String formatt_TongTien = String.format("%,d", gd.getSo_tien());
            txtSoTien.setText(formatt_TongTien+"");
        }
        else   if(gd.isLoai_giao_dich()==false){
            txtTen.setText("Tiền đi tới "+gd.getTen()+": ");
            txtNoiDung.setText(gd.getNoi_dung());
            txtTen.setBackgroundColor(Color.GRAY);
            txtNgayThang.setText(gd.getNgay_thang());
            txtNgayThang.setBackgroundColor(Color.GRAY);
            String formatt_TongTien = String.format("%,d", gd.getSo_tien());
            txtSoTien.setText(formatt_TongTien+"");
        }
        return v;
    }
}
