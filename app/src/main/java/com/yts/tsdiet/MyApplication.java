package com.yts.tsdiet;

import android.content.Context;


import com.yts.tsdiet.realm.Migration;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.sqlite.SqlitHelper;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends MultiDexApplication {
    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("TsDiet.realm")
                .schemaVersion(0)
                .migration(new Migration())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance();

        if (RealmService.getFoodList(realm).size() < 4203) {
       //     String jsonLocation = AssetJSONFile("formules.json", context);

            SqlitHelper sqlitHelper = new SqlitHelper(this);
            RealmService.saveFoodList(realm, sqlitHelper.getFoodList());
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
