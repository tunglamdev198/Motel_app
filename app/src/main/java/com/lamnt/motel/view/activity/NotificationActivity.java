package com.lamnt.motel.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lamnt.motel.R;
import com.lamnt.motel.model.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private RecyclerView rvNotification;

    private List<Notification> notifications;

    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();

        notifications = generate();

        notificationAdapter = new NotificationAdapter(this, notifications);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvNotification.setAdapter(notificationAdapter);
        rvNotification.setLayoutManager(llm);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        rvNotification = findViewById(R.id.rvListNotification);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
        }
    }

    private List<Notification> generate() {
        List<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification("Nguyễn Khắc Luyện", "đóng tiền"));
        notifications.add(new Notification("Nguyễn Khắc Luyện", "đóng tiền"));
        notifications.add(new Notification("Nguyễn Khắc Luyện", "đóng tiền"));
        notifications.add(new Notification("Nguyễn Khắc Luyện", "đóng tiền"));
        notifications.add(new Notification("Nguyễn Khắc Luyện", "đóng tiền"));

        return notifications;
    }
}
