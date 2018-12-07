package com.yts.tsdiet.realm;


import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.Record;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {


    public static List<Food> getFoodList(Realm realm) {
        return realm.copyFromRealm(realm.where(Food.class).findAll());
    }

    public static RealmResults<Food> getFoodListRealmResults(Realm realm) {
        return realm.where(Food.class).findAll();
    }

    public static List<Food> getFoodList(Realm realm, String search) {
        List<Food> foodList = new ArrayList<>();
        foodList.addAll(realm.copyFromRealm(realm.where(Food.class).equalTo("name", search).findAll()));
        foodList.addAll(realm.copyFromRealm(realm.where(Food.class).notEqualTo("name", search).contains("name", search).findAll()));
        return foodList;
    }

    public static void saveFoodList(Realm realm, ArrayList<Food> foods) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(foods);
        realm.commitTransaction();
    }

    public static Record getRecord(Realm realm, int year, int month, int day) {
        Record record = realm.where(Record.class).equalTo("year", year).equalTo("month", month).equalTo("day", day).findFirst();
        if (record == null) {
            record = new Record(year, month, day);
        } else {
            record = realm.copyFromRealm(record);
        }
        return record;
    }

    public static Record getFastRecord(Realm realm, int year, int month, int day) {
        Record record = realm.where(Record.class).equalTo("year", year).equalTo("month", month).equalTo("day", day).findFirst();
        return record;
    }

    public static List<Record> getFastRecordList(Realm realm, int type) {
        Calendar calendar = Calendar.getInstance();
        if (type == 0) {
            return realm.where(Record.class).equalTo("year", calendar.get(Calendar.YEAR)).equalTo("month", calendar.get(Calendar.MONTH)).sort("dateTime").findAll();
        } else if (type == 1) {
            return realm.where(Record.class).equalTo("year", calendar.get(Calendar.YEAR)).sort("dateTime").findAll();
        } else {
            return realm.where(Record.class).sort("dateTime").findAll();
        }
    }

    public static void saveRecord(Realm realm, Record record) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(record);
        realm.commitTransaction();
    }

}
