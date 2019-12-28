package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.utils.SharedPreferencesUtils;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ImageView imgAvatar;
    private TextView txtUserName;

    private EditText edtTen;
    private EditText edtDiaChi;
    private EditText edtSDT;
    private EditText edtCMND;
    private EditText edtNgheNghiep;
    private EditText edtMatKhau;

    private Button btnDone;

    private String maKT;
    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);
        initViews();
    }

    private void initViews() {

        btnBack = findViewById(R.id.btnBack);
        imgAvatar = findViewById(R.id.imgAvatar);
        imgAvatar.setImageResource(R.drawable.img_ronaldo);
        txtUserName = findViewById(R.id.userName);
        edtTen = findViewById(R.id.edtTen);
        edtDiaChi = findViewById(R.id.edtDiaChi);

        edtSDT = findViewById(R.id.edtSDT);
        edtCMND = findViewById(R.id.edtCMND);
        edtNgheNghiep = findViewById(R.id.edtNgheNghiep);

        edtMatKhau = findViewById(R.id.edtMatKhau);

        btnDone = findViewById(R.id.btnDone);

        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        Customer customer = (Customer) getIntent().getSerializableExtra(Key.KEY_CUSTOMER);
        maKT = customer.getMaKT();
        manager = customer.getMaQLy();

        txtUserName.setText(customer.getHoTen());
        edtTen.setText(customer.getHoTen());
        edtDiaChi.setText(customer.getDiaChi());
        edtSDT.setText(customer.getSdt());
        edtCMND.setText(customer.getCmnd());
        edtNgheNghiep.setText(customer.getNgheNghiep());
        edtMatKhau.setText(customer.getMatKhau());

        edtTen.setSelection(edtTen.getText().length());
        edtDiaChi.setSelection(edtDiaChi.getText().length());
        edtSDT.setSelection(edtSDT.getText().length());
        edtMatKhau.setSelection(edtMatKhau.getText().length());
        edtNgheNghiep.setSelection(edtNgheNghiep.getText().length());
        edtCMND.setSelection(edtCMND.getText().length());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnDone:
                final String ten = edtTen.getText().toString().trim();
                final String diaChi = edtDiaChi.getText().toString().trim();
                final String sdt = edtSDT.getText().toString().trim();
                final String cmnd = edtCMND.getText().toString().trim();
                final String nghenghiep = edtNgheNghiep.getText().toString().trim();
                final String matKhau = edtMatKhau.getText().toString().trim();

                Client.getService().editCustomer(maKT,matKhau,ten,diaChi,cmnd,sdt,nghenghiep,manager.getMaQL()).enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }
                });

                showProgressDialog();
                break;
        }
    }

    private void showProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
                finish();
            }

        }).start();
    }
}
