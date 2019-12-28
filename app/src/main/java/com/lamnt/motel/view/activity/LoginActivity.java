package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.utils.SharedPreferencesUtils;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_REGISTER = 1000;
    public static final String TAG = "LoginActivity";

    private EditText edtPhoneNumber;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView txtForgotPassword;
    private TextView txtRegister;
    private ImageView btnBack;
    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        
    }

    private void initViews() {
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

    }

    private void loginProcess() {
        final String phoneNumber = edtPhoneNumber.getText().toString();
        final String password = edtPassword.getText().toString();

        Log.d(TAG, phoneNumber + password);
        if (phoneNumber.isEmpty() && password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Client.getService().checkLogin(phoneNumber, password).enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                manager = response.body();
                if (manager == null) {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(Key.KEY_MANAGER, manager);
                    SharedPreferencesUtils.savePhoneNumber(manager.getSdt(), LoginActivity.this);
                    SharedPreferencesUtils.savePassword(manager.getMatKhau(), LoginActivity.this);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Manager> call, Throwable t) {

            }
        });
    }


    private void showProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }

        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnLogin:
                loginProcess();
                break;

            case R.id.txtRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra(Key.KEY_PHONE_NUMBER, edtPhoneNumber.getText().toString());
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_REGISTER:
                    Manager m = (Manager) data.getSerializableExtra(Key.KEY_MANAGER);
                    String phoneNumber = m.getSdt();
                    String password = m.getMatKhau();
                    edtPhoneNumber.setText(phoneNumber);
                    edtPassword.setText(password);
                    Client.getService().checkLogin(phoneNumber, password).enqueue(new Callback<Manager>() {
                        @Override
                        public void onResponse(Call<Manager> call, Response<Manager> response) {
                            manager = response.body();
                        }

                        @Override
                        public void onFailure(Call<Manager> call, Throwable t) {

                        }
                    });
                    break;

                default:
                    super.onActivityResult(requestCode, resultCode, data);

            }
        }
    }
}


