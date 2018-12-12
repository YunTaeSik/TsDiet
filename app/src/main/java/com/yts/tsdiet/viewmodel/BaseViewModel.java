package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.google.android.gms.ads.AdRequest;
import com.yts.tsdiet.data.TSLiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BaseViewModel extends ViewModel {
    public AdRequest mAdRequest;

    public TSLiveData<Boolean> isLoading = new TSLiveData<>(false);
    public TSLiveData<Boolean> isEmpty = new TSLiveData<>(true);

    public void setAdRequest(AdRequest mAdRequest) {
        this.mAdRequest = mAdRequest;
    }

    public void finish(View view) {
        Context context = view.getContext();
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).onBackPressed();
        }
    }

    public void finish(Context context) {
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

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
