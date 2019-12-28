package com.lamnt.motel.model;

import java.io.Serializable;
import java.util.UUID;

public class Customer implements Serializable {
    private String maKT;
    private String matKhau;
    private String hoTen;
    private String diaChi;
    private String cmnd;
    private String sdt;
    private String ngheNghiep;
    private Manager maQLy;

    public Customer() {
    }

    public Customer(String matKhau, String hoTen, String diaChi
            , String cmnd, String sdt, String ngheNghiep, Manager maQLy) {
        this.maKT = UUID.randomUUID().toString();
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

    @Override
    public String toString() {
        return "Customer{" +
                "maKT='" + maKT + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", cmnd='" + cmnd + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngheNghiep='" + ngheNghiep + '\'' +
                ", maQLy=" + maQLy +
                '}'+"\n";
    }
}
