package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.interactor.SearchCallback;
import com.yts.tsdiet.realm.RealmService;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FoodListViewModel extends BaseViewModel {
    public TSLiveData<List<Food>> mFoodList = new TSLiveData<>();
    public TSLiveData<Boolean> isSearching = new TSLiveData<>(false);
    private SearchCallback mSearchCallback;


    public void initList(Realm realm) {
        mFoodList.postValue(RealmService.getFoodList(realm));
    }

    public void setSearchCallback(SearchCallback searchCallback) {
        mSearchCallback = searchCallback;
    }

    public void search(CharSequence charSequence) {
        String search = charSequence.toString();
        if (mSearchCallback != null) {
            mSearchCallback.search(search);
        }
    }
}
