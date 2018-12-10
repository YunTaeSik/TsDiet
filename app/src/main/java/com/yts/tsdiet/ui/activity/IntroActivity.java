package com.yts.tsdiet.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.yts.tsdiet.BaseActivity;
import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.IntroBinding;

import java.util.concurrent.TimeUnit;

import androidx.databinding.DataBindingUtil;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class IntroActivity extends BaseActivity {
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntroBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        binding.animationLoader.setSpeed(1.5f);
        mCompositeDisposable = new CompositeDisposable();
        mCompositeDisposable.add(Single.timer(1650, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        finish();
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                    }
                }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
