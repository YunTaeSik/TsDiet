package com.yts.tsdiet.viewmodel.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.ui.activity.GoalSettingActivity;
import com.yts.tsdiet.utils.ShowIntent;
import com.yts.tsdiet.viewmodel.BaseViewModel;

public class SettingViewModel extends BaseViewModel {
    public TSLiveData<String> version = new TSLiveData<>();

    public void setVersion(Context context) {
        try {
            version.setValue(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startGoalSetting(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(view.getContext(), GoalSettingActivity.class);
        context.startActivity(intent);
    }

    public void onClickContactUs(View view) {
        ShowIntent.emailSend(view.getContext());
    }

    public void onClickReview(View view) {
        ShowIntent.reviews(view.getContext());
    }

    public void onClickInvite(View view) {
        ShowIntent.invite(view.getContext());
    }
}
