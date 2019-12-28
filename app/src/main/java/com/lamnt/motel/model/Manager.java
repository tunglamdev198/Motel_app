package com.lamnt.motel.model;

import java.io.Serializable;
import java.util.UUID;

public class Manager implements Serializable {

    private String maQL;

    private String matKhau;

    private String tenQLy;

    private String diaChi;

    private String sdt;

    public Manager() {
    }

    public Manager(String matKhau, String tenQLy, String diaChi, String sdt) {
        this.maQL = UUID.randomUUID().toString();
        this.matKhau = matKhau;
        this.tenQLy = tenQLy;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenQLy() {
        return tenQLy;
    }

    public void setTenQLy(String tenQLy) {
        this.tenQLy = tenQLy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "maQL='" + maQL + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", tenQLy='" + tenQLy + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                '}'+"\n";
    }
}
