package uk.co.tezk.mybarcalc;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by tezk on 10/07/17.
 */

public class BarCalcApplication extends Application {

    private static BarCalcApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();

        this.application = this;
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                //.migration(new BarCalRealmMigration())
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .name("bar_calc_realm.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);

    }

    public static BarCalcApplication getApplication() {
        return application;
    }
}
