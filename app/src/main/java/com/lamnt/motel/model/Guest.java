package com.lamnt.motel.model;

public class Guest {

    private String maKT;

    private String matKhau;

    private String hoTen;

    private String diaChi;

    private String cmnd;

    private String sdt;

    private String ngheNghiep;

    private Manager maQLy;

    public Guest() {
    }

    public Guest(String maKT, String matKhau, String hoTen, String diaChi, String cmnd, String sdt, String ngheNghiep, Manager maQLy) {
        this.maKT = maKT;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.ngheNghiep = ngheNghiep;
        this.maQLy = maQLy;
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public Manager getMaQLy() {
        return maQLy;
    }

    public void setMaQLy(Manager maQLy) {
        this.maQLy = maQLy;
    }
}
