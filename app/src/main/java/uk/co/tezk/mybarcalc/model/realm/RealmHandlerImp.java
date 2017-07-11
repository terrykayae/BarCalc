package uk.co.tezk.mybarcalc.model.realm;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import uk.co.tezk.mybarcalc.model.Category;

/**
 * Created by tezk on 10/07/17.
 */

public class RealmHandlerImp implements RealmHandler {
    private static RealmHandlerImp mRealmHandlerImp = null;

    private Realm realm;

    private RealmHandlerImp() {
        realm = Realm.getDefaultInstance();

    }

    public static RealmHandlerImp getInstance() {
        if (mRealmHandlerImp == null) {
            mRealmHandlerImp = new RealmHandlerImp();
        }

        return mRealmHandlerImp;
    }

    @Override
    public Observable<Category> getCategoryList() {
        if (realm == null) {
            throw new RuntimeException("Realm not initialised");
        }

        RealmResults<Category> results = realm.where(Category.class)
                .findAllAsync();

        return Observable.fromIterable(results);
    }

    @Override
    public void addCategory(final Category category) {
        if (realm == null) {
            throw new RuntimeException("Realm not initialised");
        }

        if (category.isManaged())
            return;

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(category);
            }
        });
    }

    public void close() {
        if (realm!=null) {
            realm.close();
            realm = null;
        }
    }


}
