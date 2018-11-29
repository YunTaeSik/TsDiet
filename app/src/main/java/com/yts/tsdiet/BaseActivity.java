package com.yts.tsdiet;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;

public class BaseActivity extends AppCompatActivity {
    public CompositeDisposable mCompositeDisposable;
    public Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCompositeDisposable = new CompositeDisposable();
        mRealm = Realm.getDefaultInstance();

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
    }
}
