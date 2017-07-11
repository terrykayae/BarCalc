package uk.co.tezk.mybarcalc.model.realm;

import io.reactivex.Observable;
import uk.co.tezk.mybarcalc.model.Category;

/**
 * Created by tezk on 10/07/17.
 */

public interface RealmHandler {

    public Observable<Category> getCategoryList() ;
    public void addCategory(Category category) ;
}
