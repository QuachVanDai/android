package com.example.quachvandai_04;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_04 extends BaseAdapter {
    Activity context;
    ArrayList<hoadon_2409> list;

    public Adapter_04(Activity context, ArrayList<hoadon_2409> list) {
        this.context = context;
        this.list = list;
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
        if(v == null){}
            v = inflater.inflate(R.layout.list_hoa_don, null);
        TextView txtTen = ( TextView) v.findViewById(R.id.txtTen);
        TextView txtPhong = ( TextView) v.findViewById(R.id.txtPhong);
        TextView txtTongTien = ( TextView) v.findViewById(R.id.txtTongTien);
        hoadon_2409 hoadon = list.get(position);
        txtPhong.setText("phòng: "+hoadon.getSoPhong()+"");
        txtTen.setText(hoadon.getHoTen() );
        String formatt_TongTien = String.format("%,d", hoadon.getDonGia() * hoadon.getSoNgayLuuTru());
        txtTongTien.setText(formatt_TongTien+"");
        return v;
    }
}
