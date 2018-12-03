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
    private double totalKcal;
    private double totalCarbohydrate;
    private double totalProtein;
    private double totalFat;

    private RealmList<RecordFood> recordFoodList = new RealmList<>();

    public Record() {
    }

    public void addRecordFood(RecordFood recordFood) {
        recordFoodList.add(recordFood);
        totalKcal = totalKcal + recordFood.getKcal();
        totalCarbohydrate = totalCarbohydrate + recordFood.getCarbohydrate();
        totalProtein = totalProtein + recordFood.getProtein();
        totalFat = totalFat + recordFood.getFat();
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

    public double getTotalKcal() {
        return totalKcal;
    }

    public void setTotalKcal(double totalKcal) {
        this.totalKcal = totalKcal;
    }

    public double getTotalCarbohydrate() {
        return totalCarbohydrate;
    }

    public void setTotalCarbohydrate(double totalCarbohydrate) {
        this.totalCarbohydrate = totalCarbohydrate;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public RealmList<RecordFood> getRecordFoodList() {
        return recordFoodList;
    }

    public void setRecordFoodList(RealmList<RecordFood> recordFoodList) {
        this.recordFoodList = recordFoodList;
    }
}
