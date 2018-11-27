package com.yts.tsdiet.data.model;

import com.airbnb.lottie.animation.content.Content;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Record extends RealmObject {
    @PrimaryKey
    private long createTime;

    private int year;
    private int month;
    private int day;

    private double weight;

    private RealmList<RecordFood> recordFoodList = new RealmList<>();

    public Record() {
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public RealmList<RecordFood> getRecordFoodList() {
        return recordFoodList;
    }

    public void setRecordFoodList(RealmList<RecordFood> recordFoodList) {
        this.recordFoodList = recordFoodList;
    }
}
