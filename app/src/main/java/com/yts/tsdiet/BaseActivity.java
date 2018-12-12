package com.yts.tsdiet;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.ads.AdRequest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {
    public CompositeDisposable mCompositeDisposable;
    public Realm mRealm;
    protected AdRequest mAdRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeDisposable = new CompositeDisposable();
        mRealm = Realm.getDefaultInstance();
        mAdRequest = new AdRequest.Builder().addTestDevice("1AAF5C26F373E0C2E84FCD5A8FB409BF").addTestDevice("12BFF7609B86B194323D90FCB8C3BFD7").build();

    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
