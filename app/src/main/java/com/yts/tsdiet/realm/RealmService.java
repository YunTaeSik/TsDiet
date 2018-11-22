package com.yts.tsdiet.realm;


import com.yts.tsdiet.data.model.Food;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RealmService {

    public static List<Food> getFoodList(Realm realm) {
        return realm.copyFromRealm(realm.where(Food.class).findAll());
    }

    public static void saveFoodList(Realm realm, ArrayList<Food> foods) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foods);
        realm.commitTransaction();
    }
}
