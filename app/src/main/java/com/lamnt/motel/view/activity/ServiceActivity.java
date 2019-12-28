package com.lamnt.motel.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.adapter.ServiceAdapter;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.ServiceMotel;
import com.lamnt.motel.webservice.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private ImageView btnAdd;

    private TextView txtStatus;

    private int index;

    private Intent intent;

    private RecyclerView rvListService;
    private ServiceAdapter adapter;
    private List<ServiceMotel> services;

    private static final int REQUEST_CODE_ADD_SERVICE = 100;
    private static final int REQUEST_CODE_EDIT_SERVICE = 109;
    private static final String TAG = "ServiceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
        configRV();
    }

    public void initView() {
        intent = new Intent(this, AddServiceActivity.class);
        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);
        txtStatus = findViewById(R.id.txtStatus);

        rvListService = findViewById(R.id.rvListService);

        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    private void configRV(){
        Client.getService().getService().enqueue(new Callback<List<ServiceMotel>>() {
            @Override
            public void onResponse(Call<List<ServiceMotel>> call, Response<List<ServiceMotel>> response) {
                services = response.body();
                if(services.size()==0){
                    txtStatus.setVisibility(View.VISIBLE);
                }
                else {
                    txtStatus.setVisibility(View.GONE);
                }
                adapter = new ServiceAdapter(ServiceActivity.this, services);
                rvListService.setAdapter(adapter);
                registerForContextMenu(rvListService);

                adapter.setOnItemClickListenner(new ServiceAdapter.OnItemClickListenner() {
                    @Override
                    public void onItemClicked(int position) {
                        index = position;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ServiceMotel>> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                intent.putExtra(Key.KEY_MODE, "add");
                startActivityForResult(intent, REQUEST_CODE_ADD_SERVICE);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.btnBack:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADD_SERVICE:
                if (resultCode == Activity.RESULT_OK) {
                    Client.getService().getService().enqueue(new Callback<List<ServiceMotel>>() {
                        @Override
                        public void onResponse(Call<List<ServiceMotel>> call, Response<List<ServiceMotel>> response) {
                            services = response.body();
                            adapter.notifyItemChanged(services.size());
                        }

                        @Override
                        public void onFailure(Call<List<ServiceMotel>> call, Throwable t) {

                        }
                    });

                }
                break;

            case REQUEST_CODE_EDIT_SERVICE:
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuEdit:
                ServiceMotel service = services.get(index);
                intent.putExtra(Key.KEY_MODE, "edit");
                intent.putExtra(Key.KEY_SERVICE, service);
                startActivityForResult(intent, REQUEST_CODE_EDIT_SERVICE);
                break;

            case R.id.mnuDelete:
                deleteService();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteService(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setTitle("Xóa dịch vụ")
                .setMessage("Bạn có chắc chắn muốn xóa dịch vụ "+services.get(index).getTenDV()+" không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Client.getService().deleteServiceMotel(services.get(index).getMaDV()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configRV();
    }
}
