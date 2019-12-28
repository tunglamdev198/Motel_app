package com.lamnt.motel.model;

public class Contract {
    private String maHopDong;

    private String maKT;

    private String tenKT;


    private Room maPhong;


    private String ngayThue;

    private String ngayTra;

    public Contract() {
    }

    public Contract(String maHopDong, String maKT, String tenKT, Room maPhong, String ngayThue, String ngayTra) {
        this.maHopDong = maHopDong;
        this.maKT = maKT;
        this.tenKT = tenKT;
        this.maPhong = maPhong;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public Room getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(Room maPhong) {
        this.maPhong = maPhong;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
