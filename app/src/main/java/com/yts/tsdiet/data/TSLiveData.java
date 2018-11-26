package com.yts.tsdiet.data;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class TSLiveData<T> extends MutableLiveData<T> {

    public TSLiveData() {

    }

    public TSLiveData(T value) {
        setValue(value);
    }
}
