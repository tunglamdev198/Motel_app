package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lamnt.motel.R;
import com.lamnt.motel.adapter.RoomsAdapter;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.model.Room;
import com.lamnt.motel.webservice.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBack;
    private ImageView imgRefresh;
    private ImageView imgMenu;
    private RecyclerView rvListRoom;
    private SwipeRefreshLayout srlRoom;

    private List<Room> rooms;
    private Manager manager;

    private RoomsAdapter roomsAdapter;
    private FloatingActionButton btnAdd;

    private static final int REQUEST_CODE_ADD_ROOM = 105;
    private static final String TAG = "RoomManagerActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_manager);
        initViews();
        configRecyclerView();
    }

    private void configRecyclerView(){
        Client.getService().getAll().enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                rooms = response.body();
                roomsAdapter = new RoomsAdapter(RoomManagerActivity.this, rooms);
                LinearLayoutManager llm = new LinearLayoutManager(RoomManagerActivity.this,
                        LinearLayoutManager.VERTICAL, false);

                rvListRoom.setLayoutManager(llm);
                rvListRoom.setAdapter(roomsAdapter);

                roomsAdapter.setOnItemClickListenner(new RoomsAdapter.OnItemClickListenner() {
                    @Override
                    public void onItemClicked(int position, View view) {
                        switch (view.getId()) {
                            case R.id.imgView:
                            case R.id.cvRoomDetail:
                                Intent intent = new Intent(RoomManagerActivity.this,
                                        RoomDetailActivity.class);
                                Room room = rooms.get(position);
                                intent.putExtra(Key.KEY_ROOM, room);
                                intent.putExtra(Key.KEY_MANAGER, manager);
                                startActivity(intent);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                break;

                            case R.id.imgEdit:
                                Intent intent1 = new Intent(RoomManagerActivity.this,EditRoomActivity.class);
                                Room room1 = rooms.get(position);
                                intent1.putExtra(Key.KEY_ROOM, room1);
                                intent1.putExtra(Key.KEY_MANAGER, manager);
                                startActivity(intent1);
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                break;
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {

            }
        });

    }

    private void initViews() {
        manager = (Manager) getIntent().getSerializableExtra(Key.KEY_MANAGER);
        Log.d(TAG, manager.toString());
        btnBack = findViewById(R.id.btnBack);
        imgRefresh = findViewById(R.id.imgRefresh);
        imgMenu = findViewById(R.id.imgMenu);
        btnAdd = findViewById(R.id.btnAdd);
        rvListRoom = findViewById(R.id.rvListRoom);
        srlRoom = findViewById(R.id.srlRoom);
        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        imgRefresh.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.imgRefresh:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnAdd:
                Intent intent = new Intent(this, AddRoomActivity.class);
                Log.d(TAG, manager.toString());
                intent.putExtra(Key.KEY_MANAGER, manager);
                startActivityForResult(intent, REQUEST_CODE_ADD_ROOM);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_ROOM:
                if (resultCode == Activity.RESULT_OK) {

                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        configRecyclerView();
    }


}
