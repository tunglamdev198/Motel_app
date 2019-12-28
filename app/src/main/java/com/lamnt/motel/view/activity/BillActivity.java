package com.lamnt.motel.view.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private TextView btnDone;

    private TextInputEditText edtMaHD;
    private TextInputEditText edtGiaDV;
    private TextInputEditText edtSoDienTruoc;
    private TextInputEditText edtSoDienSau;
    private TextInputEditText edtSoNuoc;
    private TextInputEditText edtGiaDien;
    private TextInputEditText edtGiaNuoc;
    private TextInputEditText edtNgayLamHD;
    private TextInputEditText edtTongTien;
    private TextInputEditText edtThanhToan;
    private TextInputEditText edtConNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        initViews();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnDone = findViewById(R.id.btnDone);
        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        edtMaHD = findViewById(R.id.edtMaHD);
        edtGiaDV = findViewById(R.id.edtGiaDV);
        edtSoDienTruoc = findViewById(R.id.edtSoDienTruoc);
        edtSoDienSau = findViewById(R.id.edtSoDienSau);
        edtSoNuoc = findViewById(R.id.edtSoNuoc);
        edtGiaDien = findViewById(R.id.edtGiaDien);
        edtGiaNuoc = findViewById(R.id.edtGiaNuoc);
        edtNgayLamHD = findViewById(R.id.edtNgayLamHD);
        edtTongTien = findViewById(R.id.edtTongTien);
        edtThanhToan = findViewById(R.id.edtThanhToan);
        edtConNo = findViewById(R.id.edtConNo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }
}
