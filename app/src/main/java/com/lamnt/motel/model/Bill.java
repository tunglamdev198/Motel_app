package com.lamnt.motel.model;

public class Bill {

    private String maHD;

    private Room maPhong;

    public Bill() {
    }

    public Bill(String maHD, Room maPhong) {
        this.maHD = maHD;
        this.maPhong = maPhong;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Room getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Room maPhong) {
        this.maPhong = maPhong;
    }
}
