package com.yts.tsdiet.ui.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.DateRangeBinding;
import com.yts.tsdiet.viewmodel.chart.ChartViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class DateRangeDialog extends Dialog implements View.OnClickListener {
    private DateRangeBinding binding;
    private ChartViewModel model;

    public DateRangeDialog(Fragment context) {
        super(context.getContext());
        setWindowHeight(84);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_date_range, null, false);
        model = ViewModelProviders.of(context).get(ChartViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(context);
        setContentView(binding.getRoot());

    }

    private void setWindowHeight(int percent) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = (screenHeight * percent / 100);
        this.getWindow().setAttributes(params);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        binding.btnMonth.setOnClickListener(this);
        binding.btnYear.setOnClickListener(this);
        binding.btnAll.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_month:
                model.setDateRange(0);
                dismiss();
                break;
            case R.id.btn_year:
                model.setDateRange(1);
                dismiss();
                break;
            case R.id.btn_all:
                model.setDateRange(2);
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }
}
