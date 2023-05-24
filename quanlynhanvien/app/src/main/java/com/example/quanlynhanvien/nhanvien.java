package com.example.quanlynhanvien;

public class nhanvien {
    private  int id;
    private String ten;
    private String sdt;
    private String DiaChi;
    private String ChucVu;
    private String GioiTinh;
    private int luong;
    private int soNgayLam;
    private String ghiChu;
    private  byte[] anh;

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public int getSoNgayLam() {
        return soNgayLam;
    }

    public void setSoNgayLam(int soNgayLam) {
        this.soNgayLam = soNgayLam;
    }

    public nhanvien(int id, String ten, String sdt, String diaChi, String chucVu, String gioiTinh, int luong, int soNgayLam, String ghiChu, byte[] anh) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        DiaChi = diaChi;
        ChucVu = chucVu;
        GioiTinh = gioiTinh;
        this.luong = luong;
        this.soNgayLam = soNgayLam;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public nhanvien(int id, String ten, String sdt, String diaChi, String chucVu, String gioiTinh, String ghiChu, byte[] anh) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        DiaChi = diaChi;
        ChucVu = chucVu;
        GioiTinh = gioiTinh;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public nhanvien(int id, String ten, String chucvu, byte[] anh) {
        this.id = id;
        this.ten = ten;
        this.ChucVu = chucvu;
        this.anh = anh;
    }
    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
