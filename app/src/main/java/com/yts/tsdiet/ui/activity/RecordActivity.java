package com.yts.tsdiet.ui.activity;

import android.content.Context;
import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.RecordBinding;
import com.yts.tsdiet.ui.adapter.RecordAdapter;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.viewmodel.RecordListViewModel;

import java.util.Calendar;
import java.util.List;

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


        observe();

        Calendar calendar = (Calendar) getIntent().getSerializableExtra(Keys.CALENDAR);
        model.setRecordList(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

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
    }
}
