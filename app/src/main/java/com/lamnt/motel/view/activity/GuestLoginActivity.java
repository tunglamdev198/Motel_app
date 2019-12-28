package com.lamnt.motel.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lamnt.motel.R;

public class GuestLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edtTenPhong;
    private TextInputEditText edtMatKhau;
    private Button btnLogin;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);
        initViews();
        if(isConnected(this)){
            return;
        }
        if(!isConnected(this)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Để sử dụng ứng dụng hiệu quả cần có kết nối Internet\nẤn OK để bật kết nối")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    private void initViews() {
        edtTenPhong = findViewById(R.id.edtTenPhong);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnLogin = findViewById(R.id.btnLogin);
        btnBack = findViewById(R.id.btnBack);
        btnLogin.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private boolean isConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info!=null && info.isConnected()){
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(wifi!= null && wifi.isConnected() || mobile == null && mobile.isConnected()){
                return true;
            }
            else  return false;
        }
        else return false;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Intent intent = new Intent(GuestLoginActivity.this,HomePageGuestActivity.class);
                startActivity(intent);
                break;

                case R.id.btnBack:
                finish();
                break;
        }
    }
}
