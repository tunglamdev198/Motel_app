package com.lamnt.motel.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.utils.SharedPreferencesUtils;
import com.lamnt.motel.view.activity.ContractDetailActivity;
import com.lamnt.motel.view.activity.CustomerActivity;
import com.lamnt.motel.view.activity.CustomerAdapter;
import com.lamnt.motel.webservice.Client;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactFragment extends Fragment implements View.OnClickListener {
    private ImageView btnAddMember;
    private RecyclerView rvListKhachTro;
    private CustomerAdapter customerAdapter;
    private List<Customer> lCustomer;

    private int index;

    private static final String TAG = "ContactFragment";
    private static final int REQUEST_CODE_EDIT_CUSTOMER = 115;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lCustomer = new ArrayList<>();
        btnAddMember = view.findViewById(R.id.btnAddMember);
        btnAddMember.setOnClickListener(this);
        rvListKhachTro = view.findViewById(R.id.rvListKhachTro);
        configRV();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_detail, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_detail:
                Intent intent = new Intent(getActivity(), ContractDetailActivity.class);
                intent.putExtra(Key.KEY_CUSTOMER, lCustomer.get(index));
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.mnuDelete:
                deleteCustomer();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void configRV(){
        Client.getService().getALlCustomer().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                lCustomer = response.body();
                customerAdapter = new CustomerAdapter(getActivity(), response.body());
                LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rvListKhachTro.setLayoutManager(llm);
                rvListKhachTro.setAdapter(customerAdapter);
                registerForContextMenu(rvListKhachTro);

                customerAdapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        index = position;
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

            }
        });

    }

    private void deleteCustomer() {
        final Customer customer = lCustomer.get(index);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true)
                .setTitle("Xóa khách thuê")
                .setMessage("Bạn có muốn xóa " + customer.getHoTen() + " khỏi danh sách liên lạc không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        Client.getService().deleteCustomer(customer.getMaKT()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                customerAdapter.notifyItemRemoved(index);
                                dialog.dismiss();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddMember:
                Client.getService().checkLogin(SharedPreferencesUtils.getPhoneNumber(getActivity()),
                        SharedPreferencesUtils.getPassword(getActivity())).enqueue(new Callback<Manager>() {
                    @Override
                    public void onResponse(Call<Manager> call, Response<Manager> response) {
                        Manager manager = response.body();
                        Intent intent = new Intent(getActivity(), CustomerActivity.class);
                        intent.putExtra(Key.KEY_MANAGER,manager);
                        startActivity(intent);
                        getActivity().overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }

                    @Override
                    public void onFailure(Call<Manager> call, Throwable t) {

                    }
                });
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        configRV();
        Log.d(TAG, "onResume: ");
    }

}
