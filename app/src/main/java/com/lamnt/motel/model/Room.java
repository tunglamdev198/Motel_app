package com.lamnt.motel.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String maPhong;

    private Manager maQLy;

    private Double dienTich;

    private int soNguoi;

    private Long giaPhong;

    public Room() {
    }

    public Room(String maPhong, Manager maQLy, Double dienTich, int soNguoi, Long giaPhong) {
        this.maPhong = maPhong;
        this.maQLy = maQLy;
        this.dienTich = dienTich;
        this.soNguoi = soNguoi;
        this.giaPhong = giaPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Manager getMaQLy() {
        return maQLy;
    }

    public void setMaQLy(Manager maQLy) {
        this.maQLy = maQLy;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public Long getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(Long giaPhong) {
        this.giaPhong = giaPhong;
    }
}
