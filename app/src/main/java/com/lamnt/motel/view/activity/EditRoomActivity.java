package com.lamnt.motel.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditRoomActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;

    private EditText edtTenPhong;
    private EditText edtGiaPhong;
    private EditText edtDienTich;
    private EditText edtSoNguoi;

    private Button btnDone;

    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        initViews();
    }

    private void initViews() {
        manager = (Manager) getIntent().getSerializableExtra(Key.KEY_MANAGER);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        edtTenPhong = findViewById(R.id.edtTenPhong);
        edtGiaPhong = findViewById(R.id.edtGiaPhong);
        edtDienTich = findViewById(R.id.edtDienTich);
        edtSoNguoi = findViewById(R.id.edtSoNguoi);

        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra(Key.KEY_ROOM);
        edtTenPhong.setText(room.getMaPhong());
        edtDienTich.setText(room.getDienTich()+"");
        edtGiaPhong.setText(room.getGiaPhong()+"");
        edtSoNguoi.setText(room.getSoNguoi()+"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnDone:
                String tenPhong = edtTenPhong.getText().toString().trim();
                Double dienTich = Double.parseDouble(edtDienTich.getText().toString().trim());
                Long giaPhong = Long.parseLong(edtGiaPhong.getText().toString().trim());
                int soNguoi = Integer.parseInt(edtSoNguoi.getText().toString());

                Client.getService().editRoom(tenPhong,manager.getMaQL(),dienTich,soNguoi,giaPhong).enqueue(new Callback<Room>() {
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
