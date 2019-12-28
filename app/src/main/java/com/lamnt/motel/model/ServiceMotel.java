package com.lamnt.motel.model;

import java.io.Serializable;
import java.util.UUID;

public class ServiceMotel implements Serializable {

    private String maDV;

    private String tenDV;

    private Double giaDV;

    public ServiceMotel() {
    }

    public ServiceMotel(String tenDV, Double giaDV) {
        this.maDV = UUID.randomUUID().toString();
        this.tenDV = tenDV;
        this.giaDV = giaDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public Double getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(Double giaDV) {
        this.giaDV = giaDV;
    }

    @Override
    public String toString() {
        return "ServiceMotel{" +
                "maDV='" + maDV + '\'' +
                ", tenDV='" + tenDV + '\'' +
                ", giaDV=" + giaDV +
                '}'+"\n";
    }
}

