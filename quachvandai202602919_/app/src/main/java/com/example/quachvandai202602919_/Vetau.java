package com.example.quachvandai202602919_;

public class Vetau {
    private int ma;
    private String ga_di;
    private String ga_den;
    private int don_gia;
    private String ngay_thang;
    private boolean khu_hoi;


    public Vetau(int ma, String ga_di, String ga_den, int don_gia, String ngay_thang, boolean khu_hoi) {
        this.ma = ma;
        this.ga_di = ga_di;
        this.ga_den = ga_den;
        this.don_gia = don_gia;
        this.ngay_thang = ngay_thang;
        this.khu_hoi = khu_hoi;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getGa_di() {
        return ga_di;
    }

    public void setGa_di(String ga_di) {
        this.ga_di = ga_di;
    }

    public String getGa_den() {
        return ga_den;
    }

    public void setGa_den(String ga_den) {
        this.ga_den = ga_den;
    }

    public int getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    public String getNgay_thang() {
        return ngay_thang;
    }

    public void setNgay_thang(String ngay_thang) {
        this.ngay_thang = ngay_thang;
    }

    public boolean isKhu_hoi() {
        return khu_hoi;
    }

    public void setKhu_hoi(boolean khu_hoi) {
        this.khu_hoi = khu_hoi;
    }
}
