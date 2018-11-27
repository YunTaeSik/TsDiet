package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;

public class WeightViewModel extends BaseViewModel {
    public TSLiveData<Double> mWeight = new TSLiveData<>(0.0);

    public void setWeight(Double weight) {
        if (weight != null) {
            mWeight.setValue(weight);
        }
    }
}
