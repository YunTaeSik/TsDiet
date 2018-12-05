package com.yts.tsdiet.utils;

import android.content.Context;
import android.content.Intent;

import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.RecordFood;

public class SendBroadcast {
    public static final String SELECT_FOOD = "selectFood";
    public static final String SAVE_RECORD_FOOD = "saveRecordFood";
    public static final String SAVE_RECORD = "saveRecord";
    public static final String REMOVE_RECORD_FOOD = "removeRecordFood";

    public static void selectFood(Context context, Food food) {
        Intent send = new Intent(SELECT_FOOD);
        send.putExtra(Keys.FOOD, food);
        context.sendBroadcast(send);
    }

    public static void saveRecordFood(Context context, RecordFood recordFood) {
        Intent send = new Intent(SAVE_RECORD_FOOD);
        send.putExtra(Keys.RECORD_FOOD, recordFood);
        context.sendBroadcast(send);
    }

    public static void removeRecordFood(Context context, RecordFood recordFood, int position) {
        Intent send = new Intent(REMOVE_RECORD_FOOD);
        send.putExtra(Keys.RECORD_FOOD, recordFood);
        send.putExtra(Keys.POSITION, position - 1);
        context.sendBroadcast(send);
    }

    public static void saveRecord(Context context) {
        Intent send = new Intent(SAVE_RECORD);
        context.sendBroadcast(send);
    }

}
