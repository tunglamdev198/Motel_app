package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.ServiceMotel;
import com.lamnt.motel.webservice.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServiceActivity extends AppCompatActivity {
    private TextInputEditText edtServiceName;
    private TextInputEditText edtServicePrice;
    private TextInputEditText edtNote;

    private Button btnDone;
    private Button btnCancel;

    private Intent intent;
    private ServiceMotel service;

    public static final String MODE_ADD = "add";
    public static final String MODE_EDIT = "edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        initViews();
        processData();
    }

    private void initViews() {
        intent = new Intent();
        edtServiceName = findViewById(R.id.edtNameSrevice);
        edtServicePrice = findViewById(R.id.edtServicePrice);
        edtNote = findViewById(R.id.edtNote);

        btnDone = findViewById(R.id.btnDone);
        btnCancel = findViewById(R.id.btnCancel);

    }

    private void processData() {
        String mode = getIntent().getStringExtra(Key.KEY_MODE);
        switch (mode) {
            case MODE_ADD:
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addService();
                    }
                });
                break;

            case MODE_EDIT:
                btnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editService();
                    }
                });
                break;
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addService() {
        String tenDV = edtServiceName.getText().toString().trim();
        Double giaDV = Double.parseDouble(edtServicePrice.getText().toString().trim());
        intent.putExtra(Key.KEY_SERVICE_NAME, tenDV);
        intent.putExtra(Key.KEY_SERVICE_PRICE, giaDV);
        ServiceMotel serviceMotel = new ServiceMotel(tenDV, giaDV);
        Client.getService().addService(serviceMotel.getMaDV(), tenDV, giaDV).enqueue(new Callback<ServiceMotel>() {
            @Override
            public void onResponse(Call<ServiceMotel> call, Response<ServiceMotel> response) {

            }

            @Override
            public void onFailure(Call<ServiceMotel> call, Throwable t) {

            }
        });
        setResult(Activity.RESULT_OK, intent);
        Toast toast = new Toast(this);
        toast.setText("Thành công");
        toast.setGravity(Gravity.BOTTOM, 20, 40);
        toast.setDuration(android.widget.Toast.LENGTH_SHORT);
        toast.show();
        showProgressDialog();
    }

    private void editService() {

        edtServiceName.setText(service.getTenDV());
        edtServicePrice.setText(service.getGiaDV()+"");
        intent.putExtra(Key.KEY_SERVICE_NAME,edtServiceName.getText().toString().trim());
        intent.putExtra(Key.KEY_SERVICE_PRICE,Double.parseDouble(edtServicePrice.getText().toString()));
        setResult(Activity.RESULT_OK,intent);
        finish();
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
