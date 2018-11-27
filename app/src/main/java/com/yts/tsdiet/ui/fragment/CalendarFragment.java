package com.yts.tsdiet.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.CalendarListBinding;
import com.yts.tsdiet.ui.adapter.CalendarAdapter;
import com.yts.tsdiet.viewmodel.CalendarListViewModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            model.setCalendarList(new GregorianCalendar());
        }
    }

    private void observe() {
        model.mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                RecyclerView view = binding.pagerCalendar;
                CalendarAdapter adapter = (CalendarAdapter) view.getAdapter();
                if (adapter != null) {
                    adapter.setCalendarList(objects);
                } else {
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    adapter = new CalendarAdapter(objects);
                    adapter.setHasStableIds(true);
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
                LinearLayoutManager manager = (LinearLayoutManager) view.getLayoutManager();
                if (manager != null && model != null) {
                    model.setTitle(manager.findFirstCompletelyVisibleItemPosition());
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
