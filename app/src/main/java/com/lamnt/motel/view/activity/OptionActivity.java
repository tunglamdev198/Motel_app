package com.lamnt.motel.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lamnt.motel.R;
import com.lamnt.motel.utils.SharedPreferencesUtils;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOwner;
    private Button btnGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        if(SharedPreferencesUtils.getPassword(getApplicationContext()).equals("")
        && SharedPreferencesUtils.getPhoneNumber(getApplicationContext()).equals("")){
            initViews();
        }
        if(!SharedPreferencesUtils.getPassword(getApplicationContext()).equals("")
                && !SharedPreferencesUtils.getPhoneNumber(getApplicationContext()).equals("")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    }

    private void initViews() {
        btnOwner = findViewById(R.id.btnOwner);
        btnGuest = findViewById(R.id.btnGuest);

        btnOwner.setOnClickListener(this);
        btnGuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOwner:
                Intent intent = new Intent(OptionActivity.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;

            case R.id.btnGuest:
                Intent intent1 = new Intent(OptionActivity.this,GuestLoginActivity.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
        }
    }
}
