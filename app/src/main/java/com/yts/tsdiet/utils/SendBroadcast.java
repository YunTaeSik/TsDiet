package com.yts.tsdiet.utils;

import android.content.Context;
import android.content.Intent;

import com.yts.tsdiet.data.model.Food;

public class SendBroadcast {
    public static final String SELECT_FOOD = "selectFood";

    public static void selectFood(Context context, Food food) {
        Intent send = new Intent(SELECT_FOOD);
        send.putExtra(Keys.FOOD, food);
        context.sendBroadcast(send);
    }


}
