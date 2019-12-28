package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;

public class AddTermsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btnDone;
    private TextView btnCancel;
    private EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms);
        initViews();
    }

    private void initViews() {
        btnDone = findViewById(R.id.btnDone);
        btnCancel = findViewById(R.id.btnCancel);
        edtContent = findViewById(R.id.edtContent);
        btnDone.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDone:
                Intent intent = new Intent();
                intent.putExtra(Key.KEY_CONTENT,edtContent.getText().toString().trim());
                setResult(Activity.RESULT_OK,intent);
                finish();
                break;

            case R.id.btnCancel:
                finish();
                break;
        }
    }
}
