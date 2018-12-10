package com.yts.tsdiet.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.yts.tsdiet.data.TSLiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import io.realm.Realm;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BaseViewModel extends ViewModel {

    public TSLiveData<Boolean> isLoading = new TSLiveData<>(false);
    public TSLiveData<Boolean> isEmpty = new TSLiveData<>(true);
    protected Realm mRealm = Realm.getDefaultInstance();


    public void finish(View view) {
        Context context = view.getContext();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).onBackPressed();
        }
    }

    public void hideKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

}
