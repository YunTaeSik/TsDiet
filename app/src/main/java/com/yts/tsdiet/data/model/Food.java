package com.yts.tsdiet.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Food extends RealmObject {
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
}
