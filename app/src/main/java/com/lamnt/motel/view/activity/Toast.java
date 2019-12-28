package com.lamnt.motel.view.activity;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lamnt.motel.R;

public class Toast extends android.widget.Toast {
    private TextView txtDialog;

    public Toast(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_toast, null);
        setView(view);
        setGravity(Gravity.CENTER, 0, 0);
        txtDialog = view.findViewById(R.id.txtDialog);
    }

    public void setText(String text) {
        txtDialog.setText(text);
    }
}
