package com.yts.tsdiet.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.yts.tsdiet.data.model.Food;

import java.util.ArrayList;

public class SqlitHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;

    public SqlitHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<Food> getFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"idx", "name", "size", "kcal", "carbohydrate", "protein", "fat", "sugars", "salt", "cholesterol", "saturated", "trans"};
        String sqlTables = "food";
        qb.setTables(sqlTables);

        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Food bible = new Food(c.getString(0), "", c.getString(1), c.getDouble(2), c.getDouble(3), c.getDouble(4)
                            , c.getDouble(5), c.getDouble(6), c.getDouble(7), c.getDouble(8), c.getDouble(9)
                            , c.getDouble(10), c.getDouble(11));
                    foodList.add(bible);
                } while (c.moveToNext());
            }
        }
        return foodList;
    }

}
