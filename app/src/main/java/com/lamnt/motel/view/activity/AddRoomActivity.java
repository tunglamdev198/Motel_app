package com.lamnt.motel.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBack;
    private TextView btnDone;

    private TextInputEditText edtName;
    private TextInputEditText edtPrice;
    private TextInputEditText edtDienTich;
    private TextInputEditText edtNumber;

    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        initViews();
    }

    private void initViews() {
        manager = (Manager) getIntent().getSerializableExtra(Key.KEY_MANAGER);
        btnDone = findViewById(R.id.btnDone);
        btnBack = findViewById(R.id.btnBack);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtDienTich = findViewById(R.id.edtDienTich);
        edtNumber = findViewById(R.id.edtNumber);

        btnBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnDone:
                String maPhong = edtName.getText().toString().trim();
                int soNguoi = Integer.parseInt(edtNumber.getText().toString().trim());
                Long giaPhong = Long.parseLong(edtPrice.getText().toString().trim());
                Double dienTich = Double.parseDouble(edtDienTich.getText().toString().trim());
                Log.d("AAA", manager.toString());
                Client.getService().addRoom(maPhong, manager.getMaQL(), dienTich, soNguoi, giaPhong).enqueue(new Callback<Room>() {
                    @Override
                    public void onResponse(Call<Room> call, Response<Room> response) {

                    }

                    @Override
                    public void onFailure(Call<Room> call, Throwable t) {

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
