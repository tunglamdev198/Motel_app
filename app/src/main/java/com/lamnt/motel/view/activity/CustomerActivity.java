package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnDone;
    private ImageView btnBack;

    private TextInputEditText edtHoTen;
    private TextInputEditText edtDiaChi;
    private TextInputEditText edtSoCMND;
    private TextInputEditText edtSoDT;
    private TextInputEditText edtNgheNghiep;
    private TextInputEditText edtMatKhau;

    private Manager manager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        initView();
    }

    private void initView() {
        manager = (Manager) getIntent().getSerializableExtra(Key.KEY_MANAGER);
        Log.d("CustomerActivity", manager.toString());
        btnDone = findViewById(R.id.btnDone);
        btnBack = findViewById(R.id.btnBack);
        String mode = getIntent().getStringExtra(Key.KEY_MODE);

        btnDone.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSoCMND = findViewById(R.id.edtSoCMND);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtNgheNghiep = findViewById(R.id.edtNgheNghiep);
        edtMatKhau = findViewById(R.id.edtMatKhau);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:
                String hoTen = edtHoTen.getText().toString().trim();
                String cmnd = edtSoCMND.getText().toString().trim();
                String diaChi = edtDiaChi.getText().toString().trim();
                String sdt = edtSoDT.getText().toString().trim();
                String ngheNghiep = edtNgheNghiep.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();

                Customer customer = new Customer(matKhau, hoTen, diaChi, cmnd, sdt, ngheNghiep, manager);
                Client.getService().addCustomer(customer.getMaKT(), matKhau,
                        hoTen, diaChi, cmnd, sdt, ngheNghiep, manager.getMaQL()).enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {

                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }

                });
                setResult(Activity.RESULT_OK);
                Toast toast = new Toast(this);
                toast.setText("Thành công");
                toast.setDuration(android.widget.Toast.LENGTH_SHORT);
                toast.show();
                showProgressDialog();
                break;

            case R.id.btnBack:
                finish();
                break;
            default:
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
