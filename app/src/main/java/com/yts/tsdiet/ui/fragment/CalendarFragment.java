package com.yts.tsdiet.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.databinding.CalendarListBinding;
import com.yts.tsdiet.ui.activity.RecordFoodActivity;
import com.yts.tsdiet.ui.adapter.NewCalendarAdapter;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.utils.SendBroadcast;
import com.yts.tsdiet.viewmodel.CalendarListViewModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CalendarFragment extends Fragment {

    private CalendarListBinding binding;
    private CalendarListViewModel model;

    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false);
        model = ViewModelProviders.of(this).get(CalendarListViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observe();
        if (model != null) {
            model.initCalendarList();
        }
        if (getActivity() != null) {
            getActivity().registerReceiver(broadcastReceiver, getIntentFilter());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(broadcastReceiver);
        }
    }

    private void observe() {
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                NewCalendarAdapter adapter = (NewCalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
                    adapter = new NewCalendarAdapter(objects);
                    //      adapter.setHasStableIds(true);
                    view.setLayoutManager(manager);
                    view.setAdapter(adapter);
                    view.scrollToPosition(adapter.getItemCount() / 2);
                }
            }
        });

        final RecyclerView view = binding.pagerCalendar;
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) view.getLayoutManager();
                if (manager != null && model != null) {
                    int[] items = manager.findFirstCompletelyVisibleItemPositions(null);
                    if (items != null && items.length > 0) {
                        model.setTitle(items[0]);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(SendBroadcast.SAVE_RECORD)) {
                    if (model != null) {
                        model.initCalendarList();
                    }
                }
            }
        }
    };

    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SendBroadcast.SAVE_RECORD);
        return intentFilter;
    }

}
