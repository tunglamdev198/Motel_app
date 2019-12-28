package com.lamnt.motel.view.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.utils.SharedPreferencesUtils;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ImageView imgAvatar;
    private TextView txtUserName;

    private EditText edtTen;
    private EditText edtDiaChi;
    private EditText edtSDT;
    private EditText edtMatKhau;

    private Button btnDone;
    private String maQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
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
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDone = findViewById(R.id.btnDone);

        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        Manager manager = (Manager) getIntent().getSerializableExtra(Key.KEY_MANAGER);
        maQL = manager.getMaQL();
        txtUserName.setText(manager.getTenQLy());
        edtTen.setText(manager.getTenQLy());

        edtDiaChi.setText(manager.getDiaChi());
        edtSDT.setText(manager.getSdt());
        edtMatKhau.setText(manager.getMatKhau());

        edtTen.setSelection(edtTen.getText().length());
        edtDiaChi.setSelection(edtDiaChi.getText().length());
        edtSDT.setSelection(edtSDT.getText().length());
        edtMatKhau.setSelection(edtMatKhau.getText().length());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnDone:
                String ten = edtTen.getText().toString().trim();
                final String sdt = edtSDT.getText().toString().trim();
                String diaChi = edtDiaChi.getText().toString().trim();
                final String matKhau = edtMatKhau.getText().toString().trim();
                Client.getService().editManager(maQL,matKhau,ten,diaChi,sdt).enqueue(new Callback<Manager>() {
                    @Override
                    public void onResponse(Call<Manager> call, Response<Manager> response) {
                        SharedPreferencesUtils.savePhoneNumber(sdt,getApplicationContext());
                        SharedPreferencesUtils.savePassword(matKhau,getApplicationContext());
                    }

                    @Override
                    public void onFailure(Call<Manager> call, Throwable t) {

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
