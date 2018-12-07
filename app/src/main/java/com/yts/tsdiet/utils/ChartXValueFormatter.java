package com.yts.tsdiet.utils;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yts.tsdiet.data.model.Record;

import java.util.ArrayList;
import java.util.List;

public class ChartXValueFormatter implements IAxisValueFormatter {
    private List<Record> recordList = new ArrayList<>();

    public ChartXValueFormatter(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            if (value == (int) value) {
                Record record = recordList.get((int) value);
                return DateFormat.getXData(record.getDateTime());
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

}
