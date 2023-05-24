package com.example.quachvandai_202602919;

import java.time.DateTimeException;

public class thu_phi {
    private  int ma;
    private String ten_khoan;
    private String ngayThang;
    private int so_tien;
    private Boolean khoan_Thu;
    private Boolean khoan_Chi;

    public thu_phi(int ma, String ten_khoan, String ngayThang, int so_tien, Boolean khoan_Thu, Boolean khoan_Chi) {
        this.ma = ma;
        this.ten_khoan = ten_khoan;
        this.ngayThang = ngayThang;
        this.so_tien = so_tien;
        this.khoan_Thu = khoan_Thu;
        this.khoan_Chi = khoan_Chi;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen_khoan() {
        return ten_khoan;
    }

    public void setTen_khoan(String ten_khoan) {
        this.ten_khoan = ten_khoan;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public int getSo_tien() {
        return so_tien;
    }

    public void setSo_tien(int so_tien) {
        this.so_tien = so_tien;
    }

    public Boolean getKhoan_Thu() {
        return khoan_Thu;
    }

    public void setKhoan_Thu(Boolean khoan_Thu) {
        this.khoan_Thu = khoan_Thu;
    }

    public Boolean getKhoan_Chi() {
        return khoan_Chi;
    }

    public void setKhoan_Chi(Boolean khoan_Chi) {
        this.khoan_Chi = khoan_Chi;
    }
}
