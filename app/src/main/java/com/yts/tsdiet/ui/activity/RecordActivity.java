package com.yts.tsdiet.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.databinding.RecordBinding;
import com.yts.tsdiet.ui.adapter.RecordAdapter;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.utils.SendBroadcast;
import com.yts.tsdiet.viewmodel.RecordListViewModel;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecordActivity extends BaseActivity {
    private RecordBinding binding;
    private RecordListViewModel model;
    private Context mContext = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_record);
        model = ViewModelProviders.of(this).get(RecordListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        Calendar calendar = (Calendar) getIntent().getSerializableExtra(Keys.CALENDAR);
        model.getRecordList(mRealm, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        observe();
        registerReceiver(broadcastReceiver, getIntentFilter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void observe() {
        model.mRecordList.observe(this, new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> objects) {
                RecyclerView view = binding.listRecord;
                RecordAdapter adapter = (RecordAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setRecordList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(mContext);
                    adapter = new RecordAdapter(objects);
                    adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                }
            }
        });

        binding.listRecord.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isScrollDown = dy > 0;
                if (model != null && model.isFloatingVisible != null && model.isFloatingVisible.getValue() != null &&
                        model.isFloatingVisible.getValue()) {
                    if (isScrollDown) {
                        binding.btnAdd.hide();
                    } else {
                        binding.btnAdd.show();
                    }
                }

            }
        });
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(SendBroadcast.SELECT_FOOD)) {
                    Food food = intent.getParcelableExtra(Keys.FOOD);
                    Intent recordFood = new Intent(mContext, RecordFoodActivity.class);
                    recordFood.putExtra(Keys.FOOD, food);
                    startActivity(recordFood);
                } else if (action.equals(SendBroadcast.SAVE_RECORD_FOOD)) { //저장
                    RecordFood recordFood = intent.getParcelableExtra(Keys.RECORD_FOOD);
                    if (model != null) {
                        model.addRecordFood(recordFood);
                    }
                    binding.btnAdd.show();
                } else if (action.equals(SendBroadcast.REMOVE_RECORD_FOOD)) { //저장
                    RecordFood recordFood = intent.getParcelableExtra(Keys.RECORD_FOOD);
                    int position = intent.getIntExtra(Keys.POSITION, 0);
                    if (model != null) {
                        model.removeRecordFood(recordFood, position);
                    }
                }
            }
        }
    };

    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SendBroadcast.SELECT_FOOD);
        intentFilter.addAction(SendBroadcast.SAVE_RECORD_FOOD);
        intentFilter.addAction(SendBroadcast.REMOVE_RECORD_FOOD);
        return intentFilter;
    }

}
