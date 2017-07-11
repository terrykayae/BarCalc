package uk.co.tezk.mybarcalc.model.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by tezk on 10/07/17.
 *
 * Deal with migration of the Database when needed
 */

public class BarCalRealmMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        if (oldVersion == 1) {

        }
    }
}
