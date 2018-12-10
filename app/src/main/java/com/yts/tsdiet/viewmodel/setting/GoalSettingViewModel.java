package com.yts.tsdiet.viewmodel.setting;

import android.content.Context;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.utils.SendBroadcast;
import com.yts.tsdiet.utils.SharedPrefsUtils;
import com.yts.tsdiet.viewmodel.BaseViewModel;

public class GoalSettingViewModel extends BaseViewModel {
    public float initGoalWeight;
    public float initGoalkcal;

    public TSLiveData<Float> mGoalWeight = new TSLiveData<>(0.0f);
    public TSLiveData<Float> mGoalKcal = new TSLiveData<>(0.0f);

    public void initData(Context context) {
        initGoalWeight = SharedPrefsUtils.getFloatPreference(context, Keys.GOAL_WEIGHT, 0);
        initGoalkcal = SharedPrefsUtils.getFloatPreference(context, Keys.GOAL_KCAL, 0);

        mGoalWeight.setValue(initGoalWeight);
        mGoalKcal.setValue(initGoalkcal);
    }

    public void weight(CharSequence charSequence) {
        try {
            float weight = Float.parseFloat(charSequence.toString());
            mGoalWeight.setValue(weight);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kcal(CharSequence charSequence) {
        try {
            float kcal = Float.parseFloat(charSequence.toString());
            mGoalKcal.setValue(kcal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(View view) {
        Context context = view.getContext();
        try {
            SharedPrefsUtils.setFloatPreference(context, Keys.GOAL_WEIGHT, mGoalWeight.getValue());
            SharedPrefsUtils.setFloatPreference(context, Keys.GOAL_KCAL, mGoalKcal.getValue());
            SendBroadcast.saveGoal(context);
            finish(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
