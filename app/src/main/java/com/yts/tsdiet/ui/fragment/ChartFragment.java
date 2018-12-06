package com.yts.tsdiet.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.databinding.ChartBinding;
import com.yts.tsdiet.utils.ChartXValueFormatter;
import com.yts.tsdiet.utils.SendBroadcast;
import com.yts.tsdiet.viewmodel.chart.ChartViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class ChartFragment extends Fragment {

    private ChartBinding binding;
    private ChartViewModel model;

    public static ChartFragment newInstance() {
        Bundle args = new Bundle();
        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart, container, false);
        model = ViewModelProviders.of(this).get(ChartViewModel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (model != null) {
            model.initData();
        }
        observe();
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
        if (model != null) {
            model.mRecordList.observe(this, new Observer<List<Record>>() {
                @Override
                public void onChanged(List<Record> records) {
                    LineChart lineChart = binding.lineChart;

                    ArrayList<Entry> values = new ArrayList<>();

                    for (Record record : records) {
                        long dateTime = record.getDateTime();
                        float weight = (float) record.getWeight();
                        values.add(new Entry(dateTime, weight));
                    }

                    LineDataSet lineDataSet = new LineDataSet(values, getString(R.string.weight));

                    LineData lineData = new LineData();
                    lineData.addDataSet(lineDataSet);
                    lineChart.setData(lineData);

                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setValueFormatter(new ChartXValueFormatter());
                    xAxis.setLabelCount(5, true);

                    YAxis yAxis = lineChart.getAxisRight();
                    yAxis.setDrawLabels(false);
                    yAxis.setDrawAxisLine(false);
                    yAxis.setDrawGridLines(false);

                    lineChart.setVisibleXRangeMinimum(60 * 60 * 24 * 1000 * 5);
                }
            });
        }
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(SendBroadcast.SAVE_RECORD)) {
                    if (model != null) {
                        model.initData();
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
