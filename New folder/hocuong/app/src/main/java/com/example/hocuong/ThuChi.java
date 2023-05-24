package com.example.hocuong;

public class ThuChi {

        int ma;
        String tenkhoan;
        String ngaythang;
        int sotien;
        boolean thu;
        boolean chi;

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenkhoan() {
        return tenkhoan;
    }

    public void setTenkhoan(String tenkhoan) {
        this.tenkhoan = tenkhoan;
    }

    public String getNgaythang() {
        return ngaythang;
    }

    public void setNgaythang(String ngaythang) {
        this.ngaythang = ngaythang;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isChi() {
        return chi;
    }

    public void setChi(boolean chi) {
        this.chi = chi;
    }

    public ThuChi(int ma, String tenkhoan, String ngaythang, int sotien, boolean thu, boolean chi) {
        this.ma = ma;
        this.tenkhoan = tenkhoan;
        this.ngaythang = ngaythang;
        this.sotien = sotien;
        this.thu = thu;
        this.chi = chi;
    }
}
