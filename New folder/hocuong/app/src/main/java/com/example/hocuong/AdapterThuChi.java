package com.example.hocuong;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterThuChi  extends BaseAdapter {
    Context context;
    ArrayList<ThuChi> list;

    public AdapterThuChi(Context context, ArrayList<ThuChi> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview,null);
        TextView txttenkhoan = (TextView) row.findViewById(R.id.txttenkhoan);
        TextView txtngay = (TextView) row.findViewById(R.id.txtngay);
        TextView txtsotien = (TextView) row.findViewById(R.id.txtsotien);
        TextView txtkhoan = (TextView) row.findViewById(R.id.txtkhoan);

        ThuChi thuChi= list.get(i);
        txttenkhoan.setText(thuChi.getTenkhoan());
        txtngay.setText(thuChi.getNgaythang());
        txtsotien.setText(thuChi.getSotien()+"");
        if (thuChi.isThu()==true ){
            txtkhoan.setText("Thu");
            txtkhoan.setBackgroundColor(Color.RED);
            txtngay.setBackgroundColor(Color.RED);

        }else if (thuChi.isChi()==true){
            txtkhoan.setText("Chi");
            txtkhoan.setBackgroundColor(Color.GRAY);
            txtngay.setBackgroundColor(Color.GRAY);
        }
        return row;
    }


}
