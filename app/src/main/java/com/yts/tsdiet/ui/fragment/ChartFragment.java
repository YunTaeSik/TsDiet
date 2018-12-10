package com.yts.tsdiet.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.databinding.ChartBinding;
import com.yts.tsdiet.ui.dialog.DateRangeDialog;
import com.yts.tsdiet.utils.ChartValueFormatter;
import com.yts.tsdiet.utils.ChartXValueFormatter;
import com.yts.tsdiet.utils.SendBroadcast;
import com.yts.tsdiet.utils.ValueFormat;
import com.yts.tsdiet.viewmodel.chart.ChartViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class ChartFragment extends Fragment implements View.OnClickListener {

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

        binding.btnDateRange.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_date_range:
                DateRangeDialog dateRangeDialog = new DateRangeDialog(this);
                dateRangeDialog.show();
                break;
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
                    setChart(records);
                }
            });
            model.mKindType.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    setChart(model.mRecordList.getValue());
                }
            });

        }
    }

    private void setChart(List<Record> records) {
        try {
            LineChart lineChart = binding.lineChart;
            lineChart.clear();

            ArrayList<Entry> weightValues = new ArrayList<>();//차트 데이터 셋에 담겨질 데이터
            ArrayList<Entry> kcalValues = new ArrayList<>();//차트 데이터 셋에 담겨질 데이터


            for (int i = 0; i < records.size(); i++) {
                Record record = records.get(i);
                float weight = Float.parseFloat(ValueFormat.format(record.getWeight()));
                float kcal = Float.parseFloat(ValueFormat.format(record.getTotalKcal()));
                weightValues.add(new Entry(i, weight));
                kcalValues.add(new Entry(i, kcal));
            }

            /*몸무게*/

            LineDataSet weightLineDataSet = null;
            if (model != null && model.mKindType != null && model.mKindType.getValue() != null && (model.mKindType.getValue() == 0 || model.mKindType.getValue() == 2)) {
                weightLineDataSet = new LineDataSet(weightValues, getString(R.string.weight_kg)); //LineDataSet 선언
                weightLineDataSet.setColor(ContextCompat.getColor(getContext(), R.color.gray)); //LineChart에서 Line Color 설정
                weightLineDataSet.setCircleColor(ContextCompat.getColor(getContext(), R.color.gray)); // LineChart에서 Line Circle Color 설정
                weightLineDataSet.setCircleHoleColor(ContextCompat.getColor(getContext(), R.color.white)); // LineChart에서 Line Hole Circle Color 설정
                weightLineDataSet.setLineWidth(3f);
                weightLineDataSet.setCircleHoleRadius(3f);
                weightLineDataSet.setCircleRadius(5f);
            }

            //칼로리
            LineDataSet kcalLineDataSet = null;
            if (model != null && model.mKindType != null && model.mKindType.getValue() != null && (model.mKindType.getValue() == 1 || model.mKindType.getValue() == 2)) {
                kcalLineDataSet = new LineDataSet(kcalValues, getString(R.string.total_calorie_kcal)); //LineDataSet 선언
                kcalLineDataSet.setColor(ContextCompat.getColor(getContext(), R.color.blueDark)); //LineChart에서 Line Color 설정
                kcalLineDataSet.setCircleColor(ContextCompat.getColor(getContext(), R.color.blueDark)); // LineChart에서 Line Circle Color 설정
                kcalLineDataSet.setCircleHoleColor(ContextCompat.getColor(getContext(), R.color.white)); // LineChart에서 Line Hole Circle Color 설정
                kcalLineDataSet.setLineWidth(3);
                kcalLineDataSet.setCircleHoleRadius(3f);
                kcalLineDataSet.setCircleRadius(5f);
            }

            LineData lineData = new LineData(); //LineDataSet을 담는 그릇 여러개의 라인 데이터가 들어갈 수 있습니다.
            if (weightLineDataSet != null) {
                lineData.addDataSet(weightLineDataSet);
            }
            if (kcalLineDataSet != null) {
                lineData.addDataSet(kcalLineDataSet);
            }

            lineData.setValueTextColor(ContextCompat.getColor(getContext(), R.color.textColor)); //라인 데이터의 텍스트 컬러 설정
            lineData.setValueTextSize(9);
            lineData.setValueTypeface(ResourcesCompat.getFont(getContext(), R.font.bmhannapro));
            lineData.setValueFormatter(new ChartValueFormatter());

            XAxis xAxis = lineChart.getXAxis(); // x 축 설정
            xAxis.setValueFormatter(new ChartXValueFormatter(records)); //X축의 데이터를 제 가공함. new ChartXValueFormatter은 Custom한 소스
            xAxis.setLabelCount(4); //X축의 데이터를 최대 몇개 까지 나타낼지에 대한 설정 5개 force가 true 이면 반드시 보여줌
            xAxis.setTypeface(ResourcesCompat.getFont(getContext(), R.font.bmhannapro));
            xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor)); // X축 텍스트컬러설정
            xAxis.setGridColor(ContextCompat.getColor(getContext(), R.color.textColor)); // X축 줄의 컬러 설정
            //   xAxis.setGranularity(1);
            //  xAxis.setGranularityEnabled(true);
            xAxis.setAvoidFirstLastClipping(true);
            //xAxis.setTextSize(11);

            YAxis yAxisLeft = lineChart.getAxisLeft(); //Y축의 왼쪽면 설정
            yAxisLeft.setTypeface(ResourcesCompat.getFont(getContext(), R.font.bmhannapro));
            yAxisLeft.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor)); //Y축 텍스트 컬러 설정
            yAxisLeft.setGridColor(ContextCompat.getColor(getContext(), R.color.textColor)); // Y축 줄의 컬러 설정
            //yAxisLeft.setTextSize(11);

            YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
            yAxisRight.setDrawLabels(false);
            yAxisRight.setDrawAxisLine(false);
            yAxisRight.setDrawGridLines(false);
            //y축의 활성화를 제거함


            //  lineChart.setPinchZoom(fal);
            lineChart.setDescription(null); //차트에서 Description 설정 저는 따로 안했습니다.

            Legend legend = lineChart.getLegend(); //레전드 설정 (차트 밑에 색과 라벨을 나타내는 설정)
            legend.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor)); // 레전드 컬러 설정
            legend.setTypeface(ResourcesCompat.getFont(getContext(), R.font.bmhannapro));
            lineChart.setData(lineData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if (action.equals(SendBroadcast.SAVE_RECORD)) {
                    if (model != null) {
                        model.refreshData();
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
