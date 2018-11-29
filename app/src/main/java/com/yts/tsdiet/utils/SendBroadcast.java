package com.yts.tsdiet.utils;

import android.content.Context;
import android.content.Intent;

import com.yts.tsdiet.data.model.RecordFood;

public class SendBroadcast {
    public static final String SELECT_FOOD = "selectFood";

    public static void selectFood(Context context, RecordFood recordFood) {
        Intent send = new Intent(SELECT_FOOD);
        send.putExtra(Keys.RECORD_FOOD, recordFood);
        context.sendBroadcast(send);
    }


}
