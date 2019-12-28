package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Circle;
import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText edtName;
    private TextInputEditText edtPhoneNumber;
    private TextInputEditText edtAddress;
    private TextInputEditText edtPassword;
    private TextInputEditText edtConfirmPassword;
    private Button btnRegister;
    private ImageView btnBack;
    private ProgressBar prg;

    public static final String TAG = "AAA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnBack = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
        prg = findViewById(R.id.prg);
        btnRegister.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();
        edtPhoneNumber.setText(intent.getStringExtra(Key.KEY_PHONE_NUMBER));
        edtPassword.setText(intent.getStringExtra(Key.KEY_PASSWORD));
    }

    private void register() {
        final String name = edtName.getText().toString().trim();
        final String phoneNumber = edtPhoneNumber.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        final String address = edtAddress.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (name.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
        }

        if (phoneNumber.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }

        if (password.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng mật khẩu", Toast.LENGTH_SHORT).show();
        }

        if (confirmPassword.trim().isEmpty()) {
            Toast.makeText(this, "Vui lòng mật khẩu", Toast.LENGTH_SHORT).show();
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            edtConfirmPassword.setText("");
            return;
        }
        if (name.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!name.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()) {
            btnRegister.setOnClickListener(this);
            Intent intent = new Intent();
            Manager manager = new Manager(password, name, address, phoneNumber);
            intent.putExtra(Key.KEY_MANAGER, manager);
            Client.getService().addManager(manager.getMaQL(), password, name, address, phoneNumber).enqueue(new Callback<Manager>() {
                @Override
                public void onResponse(Call<Manager> call, Response<Manager> response) {
                    Log.d(TAG, response.body().toString());
                }

                @Override
                public void onFailure(Call<Manager> call, Throwable t) {

                }
            });
            setResult(Activity.RESULT_OK, intent);
            finish();
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
            case R.id.btnRegister:
                register();
                break;

            case R.id.btnBack:
                finish();
                break;

        }
    }
}
