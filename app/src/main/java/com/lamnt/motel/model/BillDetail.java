package com.lamnt.motel.model;

public class BillDetail {

    private Bill maHoaDon;


    private ServiceMotel maDV;

    private Double soDien;


    private Double soNuoc;


    private Double diaDien;


    private Double giaNuoc;

    private String ngayLamHoaDon;

    private Double tongTien;

    private Double thanhToan;

    private Double congNo;

    public BillDetail() {
    }

    public BillDetail(Bill maHoaDon, ServiceMotel maDV, Double soDien, Double soNuoc
            , Double diaDien, Double giaNuoc, String ngayLamHoaDon, Double tongTien
            , Double thanhToan, Double congNo) {
        this.maHoaDon = maHoaDon;
        this.maDV = maDV;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.diaDien = diaDien;
        this.giaNuoc = giaNuoc;
        this.ngayLamHoaDon = ngayLamHoaDon;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.congNo = congNo;
    }

    public Bill getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Bill maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public ServiceMotel getMaDV() {
        return maDV;
    }

    public void setMaDV(ServiceMotel maDV) {
        this.maDV = maDV;
    }

    public Double getSoDien() {
        return soDien;
    }

    public void setSoDien(Double soDien) {
        this.soDien = soDien;
    }

    public Double getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(Double soNuoc) {
        this.soNuoc = soNuoc;
    }

    public Double getDiaDien() {
        return diaDien;
    }

    public void setDiaDien(Double diaDien) {
        this.diaDien = diaDien;
    }

    public Double getGiaNuoc() {
        return giaNuoc;
    }

    public void setGiaNuoc(Double giaNuoc) {
        this.giaNuoc = giaNuoc;
    }

    public String getNgayLamHoaDon() {
        return ngayLamHoaDon;
    }

    public void setNgayLamHoaDon(String ngayLamHoaDon) {
        this.ngayLamHoaDon = ngayLamHoaDon;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(Double thanhToan) {
        this.thanhToan = thanhToan;
    }

    public Double getCongNo() {
        return congNo;
    }

    public void setCongNo(Double congNo) {
        this.congNo = congNo;
    }
}
