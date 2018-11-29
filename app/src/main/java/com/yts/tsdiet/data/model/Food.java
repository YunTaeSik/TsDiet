package com.yts.tsdiet.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Food extends RealmObject implements Parcelable {
    @PrimaryKey
    private String idx;

    private String group; //밥류, 면류 현재 데이터에 에러있음..

    private String name; //이름
    private double size; //크기
    private double kcal; //칼로리

    private double carbohydrate; //탄수화물
    private double protein; //단백질
    private double fat; //지방

    private double sugars; //당류
    private double salt; //나트륨
    private double cholesterol; //콜레스테롤
    private double saturated; //포화지방산
    private double trans; //트랜스 지방

    public Food() {
    }

    public Food(String idx, String group, String name, double size, double kcal, double carbohydrate, double protein, double fat, double sugars, double salt, double cholesterol, double saturated, double trans) {
        this.idx = idx;
        this.group = group;
        this.name = name;
        this.size = size;
        this.kcal = kcal;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.sugars = sugars;
        this.salt = salt;
        this.cholesterol = cholesterol;
        this.saturated = saturated;
        this.trans = trans;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSaturated() {
        return saturated;
    }

    public void setSaturated(double saturated) {
        this.saturated = saturated;
    }

    public double getTrans() {
        return trans;
    }

    public void setTrans(double trans) {
        this.trans = trans;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idx);
        dest.writeString(this.group);
        dest.writeString(this.name);
        dest.writeDouble(this.size);
        dest.writeDouble(this.kcal);
        dest.writeDouble(this.carbohydrate);
        dest.writeDouble(this.protein);
        dest.writeDouble(this.fat);
        dest.writeDouble(this.sugars);
        dest.writeDouble(this.salt);
        dest.writeDouble(this.cholesterol);
        dest.writeDouble(this.saturated);
        dest.writeDouble(this.trans);
    }

    protected Food(Parcel in) {
        this.idx = in.readString();
        this.group = in.readString();
        this.name = in.readString();
        this.size = in.readDouble();
        this.kcal = in.readDouble();
        this.carbohydrate = in.readDouble();
        this.protein = in.readDouble();
        this.fat = in.readDouble();
        this.sugars = in.readDouble();
        this.salt = in.readDouble();
        this.cholesterol = in.readDouble();
        this.saturated = in.readDouble();
        this.trans = in.readDouble();
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
