package uk.co.tezk.mybarcalc.model;

import io.realm.RealmObject;

/**
 * Created by tezk on 06/07/17.
 */

public class Order extends RealmObject {
    private Item item;
    private int amount;
    private int cost;

   // @LinkingObjects("orderList")
  //  private final RealmResults <Receipt> receipt;
}
