package com.lamnt.motel.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;

public class ListBillActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private TextView txtStatus;
    private RecyclerView rvListBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bill);
        initViews();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        txtStatus = findViewById(R.id.txtStatus);

        rvListBill = findViewById(R.id.rvListBill);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                break;
        }
    }
}
