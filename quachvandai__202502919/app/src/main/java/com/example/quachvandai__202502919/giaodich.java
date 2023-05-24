package com.example.quachvandai__202502919;

public class giaodich {
    private int ma;
    private String ten;
    private boolean loai_giao_dich;
    private String noi_dung;
    private int so_tien;
    private String ngay_thang;

    public giaodich(int ma, String ten, boolean loai_giao_dich, String noi_dung, int so_tien, String ngay_thang) {
        this.ma = ma;
        this.ten = ten;
        this.loai_giao_dich = loai_giao_dich;
        this.noi_dung = noi_dung;
        this.so_tien = so_tien;
        this.ngay_thang = ngay_thang;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isLoai_giao_dich() {
        return loai_giao_dich;
    }

    public void setLoai_giao_dich(boolean loai_giao_dich) {
        this.loai_giao_dich = loai_giao_dich;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String no_dung) {
        this.noi_dung = no_dung;
    }

    public int getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(int so_tien) {
        this.so_tien = so_tien;
    }

    public String getNgay_thang() {
        return ngay_thang;
    }

    public void setNgay_thang(String ngay_thang) {
        this.ngay_thang = ngay_thang;
    }
}
