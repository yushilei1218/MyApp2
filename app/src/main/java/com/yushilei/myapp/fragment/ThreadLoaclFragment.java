package com.yushilei.myapp.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yushilei.myapp.R;
import com.yushilei.myapp.ui.HandlerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreadLoaclFragment extends BottomSheetDialogFragment {


    public ThreadLoaclFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thread_loacl, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_thread_loacl, null, false);
        dialog.setView(view);
        ButterKnife.bind(this, view);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.height = 400;
        getDialog().getWindow().setAttributes(params);
    }

    @OnClick(R.id.threadLocal_btn)
    public void btnOnClick() {
        HandlerActivity.MyListener myListener = HandlerActivity.threadLocal.get();
        if (myListener != null) {
            myListener.update("数据来至Fg");
        }
        getDialog().dismiss();
    }
}
