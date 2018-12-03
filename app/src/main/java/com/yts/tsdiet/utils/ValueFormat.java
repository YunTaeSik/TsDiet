package com.yts.tsdiet.utils;

import java.text.DecimalFormat;

public class ValueFormat {
    public static String format(double value) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            return decimalFormat.format(value);
        } catch (Exception e) {
            return "0.0";
        }
    }
}
