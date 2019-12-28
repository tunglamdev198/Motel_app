package com.lamnt.motel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;

public class ListContractActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ImageView btnAdd;
    private TextView txtStatus;
    private RecyclerView rvListContract;

    private static final int REQUEST_CODE_ADD_CONTRACT = 1002;
    private static final int REQUEST_CODE_EDIT_CONTRACT = 1003;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contract);
        initView();
    }

    public void initView() {
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);

        txtStatus = findViewById(R.id.txtStatus);
        rvListContract = findViewById(R.id.rvListContract);

        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btnAdd:
                Intent intent = new Intent(getApplication(), AddContractActivity.class);
                startActivityForResult(intent,REQUEST_CODE_ADD_CONTRACT);
                break;

            default:
                break;
        }
    }
}
