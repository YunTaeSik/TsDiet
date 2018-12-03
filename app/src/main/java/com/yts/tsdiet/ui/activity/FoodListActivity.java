package com.yts.tsdiet.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.databinding.FoodListBinding;
import com.yts.tsdiet.interactor.SearchCallback;
import com.yts.tsdiet.ui.adapter.FoodAdapter;
import com.yts.tsdiet.viewmodel.FoodListViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListActivity extends BaseActivity implements SearchCallback {
    private FoodListBinding binding;
    private FoodListViewModel model;

    private Context mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_list);
        model = ViewModelProviders.of(this).get(FoodListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        model.setSearchCallback(this);
        model.setCompositeDisposable(mCompositeDisposable);
        model.initList(mRealm);
        observe();
    }

    private void observe() {
        model.mFoodList.observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> objects) {
                RecyclerView view = binding.listFood;
                FoodAdapter adapter = (FoodAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setFoodList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    adapter = new FoodAdapter(objects);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });
        binding.listFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                hideKeyboard();
            }
        });
    }

    @Override
    public void search(String search) {
        try {
            ((FoodAdapter) binding.listFood.getAdapter()).getFilter().filter(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
