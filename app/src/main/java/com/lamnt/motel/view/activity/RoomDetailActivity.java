package com.lamnt.motel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lamnt.motel.R;
import com.lamnt.motel.adapter.ServiceAdapter;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.model.ServiceMotel;
import com.lamnt.motel.webservice.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomDetailActivity extends AppCompatActivity {
    private ImageView imgAppBar;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private TextView txtFloor;

    private TextView txtPrice;
    private TextView txtDienTich;
    private TextView txtMember;
    private TextView txtStatus;
    private RecyclerView rvListService;
    private ServiceAdapter adapter;
    private List<ServiceMotel> sercices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        initViews();
        configRV();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        imgAppBar = findViewById(R.id.imgAppBar);
        imgAppBar.setImageResource(R.drawable.demo);

        txtFloor = findViewById(R.id.txtFloor);
        txtStatus = findViewById(R.id.txtStatus);
        txtMember = findViewById(R.id.txtMember);

        txtPrice = findViewById(R.id.txtPrice);
        txtDienTich = findViewById(R.id.txtDienTich);

        rvListService = findViewById(R.id.rvListService);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Room room = (Room) intent.getSerializableExtra(Key.KEY_ROOM);
        getSupportActionBar().setTitle(room.getMaPhong());
        collapsingToolbar.setTitle(room.getMaPhong());
        txtFloor.setText("Tầng " + room.getMaPhong().substring(1, 2));
        txtMember.setText(room.getSoNguoi() + " thành viên");
        txtPrice.setText(room.getGiaPhong() + "");
        txtDienTich.setText(room.getDienTich() + " m2");
    }
    private void configRV(){
        Client.getService().getService().enqueue(new Callback<List<ServiceMotel>>() {
            @Override
            public void onResponse(Call<List<ServiceMotel>> call, Response<List<ServiceMotel>> response) {
                sercices = response.body();
                if (sercices.size() == 0) {
                    txtStatus.setVisibility(View.VISIBLE);
                } else {
                    txtStatus.setVisibility(View.GONE);
                }
                adapter = new ServiceAdapter(RoomDetailActivity.this, sercices);
                rvListService.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ServiceMotel>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuXemHoaDon:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mnuXemHopDong:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
