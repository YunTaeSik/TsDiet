package com.yts.tsdiet.ui.activity;

import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.databinding.RecordFoodAddBinding;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.viewmodel.RecordFoodViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

public class RecordFoodActivity extends BaseActivity {
    private RecordFoodAddBinding binding;
    private RecordFoodViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_record_food);
        model = ViewModelProviders.of(this).get(RecordFoodViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner((LifecycleOwner) this);

        model.setAdRequest(mAdRequest);

        Food food = getIntent().getParcelableExtra(Keys.FOOD);

        model.setFood(food);
    }
}
