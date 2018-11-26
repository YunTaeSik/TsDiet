package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.view.View;


import com.yts.tsdiet.data.TSLiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import io.realm.Realm;

public class BaseViewModel extends ViewModel {

    public TSLiveData<Boolean> isLoading = new TSLiveData<>(false);
    protected Realm mRealm = Realm.getDefaultInstance();


    public void finish(View view) {
        Context context = view.getContext();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).onBackPressed();
        }
    }

}
