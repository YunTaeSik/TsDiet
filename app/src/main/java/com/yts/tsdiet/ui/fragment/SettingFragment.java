package com.yts.tsdiet.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.SettingBinding;
import com.yts.tsdiet.viewmodel.setting.SettingViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SettingFragment extends Fragment {
    private SettingBinding binding;
    private SettingViewModel model;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        model = ViewModelProviders.of(this).get(SettingViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}
