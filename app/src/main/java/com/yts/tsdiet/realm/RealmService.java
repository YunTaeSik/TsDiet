package com.yts.tsdiet.realm;


import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.Record;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class RealmService {

    public static List<Food> getFoodList(Realm realm) {
        return realm.copyFromRealm(realm.where(Food.class).findAll());
    }

    public static List<Food> getFoodList(Realm realm, String search) {
        List<Food> foodList = getFoodList(realm);
        List<Food> resultList = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getName().toLowerCase().contains(search.toLowerCase())) {
                resultList.add(food);
            }
        }
        return resultList;
    }

    public static void saveFoodList(Realm realm, ArrayList<Food> foods) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foods);
        realm.commitTransaction();
    }

    public static Record getRecord(Realm realm, int year, int month, int day) {
        Record record = realm.where(Record.class).equalTo("year", year).equalTo("month", month).equalTo("day", day).findFirst();
        if (record == null) {
            record = new Record();
        } else {
            record = realm.copyFromRealm(record);
        }
        return record;
    }

}
