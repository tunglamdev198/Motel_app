package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;

public class TermActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ImageView btnEdit;

    private TextView txtContent;

    private CardView cvAddTerms;

    public static final int REQUEST_CODE_ADD_TERMS = 105;
    public static final int REQUEST_CODE_EDIT_TERMS = 106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        initViews();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
        txtContent = findViewById(R.id.txtContent);
        cvAddTerms = findViewById(R.id.cvAddTerms);
        if (txtContent.getText().toString().trim().isEmpty()){
            cvAddTerms.setVisibility(View.VISIBLE);
        }
        else {
            cvAddTerms.setVisibility(View.GONE);
        }
        btnBack.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        cvAddTerms.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

                case R.id.cvAddTerms:
                    Intent intent = new Intent(this,AddTermsActivity.class);
                    startActivityForResult(intent,REQUEST_CODE_ADD_TERMS);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    cvAddTerms.setVisibility(View.GONE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_CODE_ADD_TERMS:
                if(resultCode == Activity.RESULT_OK){
                    txtContent.setText(data.getStringExtra(Key.KEY_CONTENT));
                }
                break;
            default: super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
