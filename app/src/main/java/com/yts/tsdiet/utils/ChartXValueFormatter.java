package com.yts.tsdiet.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class ChartXValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return DateFormat.getXData((long) value);
        } catch (Exception e) {
            return " ";
        }
    }
}
