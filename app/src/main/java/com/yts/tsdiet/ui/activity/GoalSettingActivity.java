package com.yts.tsdiet.ui.activity;

import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.GoalSettingBinding;
import com.yts.tsdiet.viewmodel.setting.GoalSettingViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class GoalSettingActivity extends BaseActivity {

    private GoalSettingBinding binding;
    private GoalSettingViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_goal_setting);
        model = ViewModelProviders.of(this).get(GoalSettingViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        model.setAdRequest(mAdRequest);

        model.initData(this);

        observe();
    }

    private void observe() {

    }

}
