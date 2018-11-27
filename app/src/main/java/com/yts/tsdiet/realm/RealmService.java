package com.yts.tsdiet.realm;


import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.Record;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class RealmService {
    private static Realm mRealm = Realm.getDefaultInstance();

    public static List<Food> getFoodList(Realm realm) {
        return realm.copyFromRealm(realm.where(Food.class).findAll());
    }

    public static void saveFoodList(Realm realm, ArrayList<Food> foods) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foods);
        realm.commitTransaction();
    }

    public static Record getRecord(int year, int month, int day) {
        Record record = mRealm.where(Record.class).equalTo("year", year).equalTo("month", month).equalTo("day", day).findFirst();
        if (record == null) {
            record = new Record();
        } else {
            record = mRealm.copyFromRealm(record);
        }
        return record;
    }
}
