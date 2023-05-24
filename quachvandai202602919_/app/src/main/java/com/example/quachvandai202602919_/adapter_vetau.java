package com.example.quachvandai202602919_;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_vetau extends BaseAdapter {
    Activity context;
    ArrayList<Vetau> lst_vetau;

    public adapter_vetau(Activity context, ArrayList<Vetau> lst_vetau) {
        this.context = context;
        this.lst_vetau = lst_vetau;
    }


    @Override
    public int getCount() {
        return lst_vetau.size();
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
            TextView txt_gadi_gaden= ( TextView) v.findViewById(R.id.txt_gadi_gaden);
            TextView txt_ten_chieu = ( TextView) v.findViewById(R.id.txt_ten_chieu);
            TextView txtSoTien = ( TextView) v.findViewById(R.id.txt_soTien);
            TextView txtNgayThang= ( TextView) v.findViewById(R.id.txt_ngaythang);
           Vetau vt = lst_vetau.get(position);
            if(vt.isKhu_hoi()==true)
            {
                txt_gadi_gaden.setText(vt.getGa_di()+" -> "+vt.getGa_den());
                txt_ten_chieu.setText("Khứ hồi");
                txt_gadi_gaden.setBackgroundColor(Color.RED);
                txtNgayThang.setText(vt.getNgay_thang());
                txtNgayThang.setBackgroundColor(Color.RED);
                String formatt_TongTien = String.format("%,d", vt.getDon_gia()*90/100);
                txtSoTien.setText(formatt_TongTien+"");
            }
            else   if(vt.isKhu_hoi()==false){
                txt_gadi_gaden.setText(vt.getGa_di()+" -> "+vt.getGa_den());
                txt_ten_chieu.setText("Một chiều");
                txt_gadi_gaden.setBackgroundColor(Color.GRAY);
                txtNgayThang.setText(vt.getNgay_thang());
                txtNgayThang.setBackgroundColor(Color.GRAY);
                String formatt_TongTien = String.format("%,d", vt.getDon_gia());
                txtSoTien.setText(formatt_TongTien+"");
            }

            return v;
    }
}

