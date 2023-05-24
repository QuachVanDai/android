package com.example.quachvandai_202602919;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_khoan_thu extends BaseAdapter {

Activity context;
ArrayList<thu_phi> lst_thuPhi;

    public adapter_khoan_thu(Activity context, ArrayList<thu_phi> lst_thuPhi) {
        this.context = context;
        this.lst_thuPhi = lst_thuPhi;
    }

    @Override
    public int getCount() {
        return lst_thuPhi.size();
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
        TextView txtKhoan= ( TextView) v.findViewById(R.id.txt_khoan);
        TextView txtTenKhoan = ( TextView) v.findViewById(R.id.txt_tenKhoan);
        TextView txtSoTien = ( TextView) v.findViewById(R.id.txt_soTien);
        TextView txtNgayThang= ( TextView) v.findViewById(R.id.txt_ngaythang);
        thu_phi tp = lst_thuPhi.get(position);
        if(tp.getKhoan_Thu()==false && tp.getKhoan_Chi()==true)
        {
            txtKhoan.setText("Chi: ");
            txtTenKhoan.setText(tp.getTen_khoan() );
            txtKhoan.setBackgroundColor(Color.RED);
            txtNgayThang.setText(tp.getNgayThang());
            txtNgayThang.setBackgroundColor(Color.RED);
            String formatt_TongTien = String.format("%,d", tp.getSo_tien());
            txtSoTien.setText(formatt_TongTien+"");
        }
        else   if(tp.getKhoan_Thu()==true && tp.getKhoan_Chi()==false){
            txtKhoan.setText("Thu: ");
            txtTenKhoan.setText(tp.getTen_khoan() );
             txtNgayThang.setText(tp.getNgayThang());
            txtKhoan.setBackgroundColor(Color.GRAY);
            txtNgayThang.setBackgroundColor(Color.GRAY);
            String formatt_TongTien = String.format("%,d", tp.getSo_tien());
            txtSoTien.setText(formatt_TongTien+"");
        }

        return v;
    }
}
    // Lấy ngày hiện tại
   /* Date currentDate = new Date();

    // Định dạng ngày tháng
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = sdf.format(currentDate);*/
