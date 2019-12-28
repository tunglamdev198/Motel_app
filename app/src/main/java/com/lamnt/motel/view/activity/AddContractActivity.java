package com.lamnt.motel.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lamnt.motel.R;

public class AddContractActivity extends AppCompatActivity {
    private TextView btnAction;
    private TextView btnCancel;


    private TextInputEditText edtTenPhong;
    private TextInputEditText edtTenKhach;
    private TextInputEditText edtNgayThue;
    private TextInputEditText edtNgayTra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        initViews();
    }

    private void initViews() {
        btnAction = findViewById(R.id.btnAction);
        btnCancel = findViewById(R.id.btnCancel);

        edtTenPhong = findViewById(R.id.edtTenPhong);
        edtTenKhach = findViewById(R.id.edtTenKhach);
        edtNgayThue = findViewById(R.id.edtNgayThue);
        edtNgayTra = findViewById(R.id.edtNgayTra);
    }
}
