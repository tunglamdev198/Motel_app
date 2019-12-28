package com.lamnt.motel.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamnt.motel.R;
import com.lamnt.motel.common.Key;
import com.lamnt.motel.model.Customer;
import com.lamnt.motel.model.Manager;
import com.lamnt.motel.utils.SharedPreferencesUtils;
import com.lamnt.motel.view.activity.BillActivity;
import com.lamnt.motel.view.activity.ListBillActivity;
import com.lamnt.motel.view.activity.ListContractActivity;
import com.lamnt.motel.view.activity.LoginActivity;
import com.lamnt.motel.view.activity.OptionActivity;
import com.lamnt.motel.view.activity.RoomManagerActivity;
import com.lamnt.motel.view.activity.ServiceActivity;
import com.lamnt.motel.view.activity.TermActivity;
import com.lamnt.motel.view.activity.Toast;
import com.lamnt.motel.view.activity.UserActivity;
import com.lamnt.motel.webservice.Client;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends Fragment implements View.OnClickListener {
    private CardView cvRoom;
    private CardView cvContract;
    private CardView cvNotification;
    private CardView cvBill;
    private CardView cvTerm;
    private CardView cvService;

    private ImageView btnEditProfile;
    private CircleImageView imgAvatar;
    private ImageView actionSetting;

    private TextView txtAcountName;

    private Manager manager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Client.getService().checkLogin(SharedPreferencesUtils.getPhoneNumber(getActivity()),
                SharedPreferencesUtils.getPassword(getActivity())).enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                manager = response.body();
                txtAcountName.setText(manager.getTenQLy());
            }

            @Override
            public void onFailure(Call<Manager> call, Throwable t) {

            }
        });
        cvRoom = view.findViewById(R.id.cvRoom);
        cvContract = view.findViewById(R.id.cvContract);
        cvNotification = view.findViewById(R.id.cvNotification);
        cvBill = view.findViewById(R.id.cvBill);
        cvTerm = view.findViewById(R.id.cvTerm);
        cvService = view.findViewById(R.id.cvService);
        txtAcountName = view.findViewById(R.id.txtAcountName);
        actionSetting = view.findViewById(R.id.actionSetting);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        imgAvatar.setImageResource(R.drawable.img_ronaldo);

        registerForContextMenu(actionSetting);

        cvRoom.setOnClickListener(this);
        cvContract.setOnClickListener(this);
        cvNotification.setOnClickListener(this);
        cvBill.setOnClickListener(this);
        cvTerm.setOnClickListener(this);
        cvService.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvRoom:
                Intent intent = new Intent(getActivity(), RoomManagerActivity.class);
                intent.putExtra(Key.KEY_MANAGER,manager);
                showProgressDialog(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.cvContract:
                Intent intent1 = new Intent(getActivity(), ListContractActivity.class);
                showProgressDialog(intent1);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.cvNotification:
                Intent intent2 = new Intent(getActivity(), NotificationActivity.class);
                showProgressDialog(intent2);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.cvBill:
                Intent intent3 = new Intent(getActivity(), ListBillActivity.class);
                showProgressDialog(intent3);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.cvTerm:
                Intent intent4 = new Intent(getActivity(), TermActivity.class);
                showProgressDialog(intent4);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.cvService:
                Intent intent5 = new Intent(getActivity(), ServiceActivity.class);
                showProgressDialog(intent5);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.btnEditProfile:
                Intent intent6 = new Intent(getActivity(), UserActivity.class);
                intent6.putExtra(Key.KEY_MANAGER, manager);
                showProgressDialog(intent6);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            default:
                break;
        }
    }

    private void showProgressDialog(final Intent intent) {
        final ProgressDialog dialog = new ProgressDialog(this.getActivity());
        dialog.setMessage("Loading...");
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().startActivity(intent);
                dialog.dismiss();
            }

        }).start();
    }

    private void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có muốn đăng xuất không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferencesUtils.savePassword("",getActivity());
                        SharedPreferencesUtils.savePhoneNumber("",getActivity());
                        Intent intent = new Intent(getActivity(), OptionActivity.class);
                        startActivity(intent);
                        getActivity().finish();

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

    private void deleteAccount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true)
                .setTitle("Xóa tài khoản")
                .setMessage("Bạn có chắc chắn muốn xóa tài khoản này không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Client.getService().deleteManager(manager.getMaQL()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Intent intent = new Intent(getActivity(), OptionActivity.class);
                                startActivity(intent);
                                getActivity().finish();
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_option_account, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuLogout:
                logout();
                break;

            case R.id.mnuDeleteAccount:
                deleteAccount();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        Client.getService().checkLogin(SharedPreferencesUtils.getPhoneNumber(getActivity()),
                SharedPreferencesUtils.getPassword(getActivity())).enqueue(new Callback<Manager>() {
            @Override
            public void onResponse(Call<Manager> call, Response<Manager> response) {
                manager = response.body();
                txtAcountName.setText(manager.getTenQLy());
            }

            @Override
            public void onFailure(Call<Manager> call, Throwable t) {

            }
        });
    }
}
