package uk.co.tezk.mybarcalc.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by tezk on 07/07/17.
 */

public class Receipt extends RealmObject {
        private User servedBy;
        private Date time;
        private int totalCharge;
        private int received;
        private int change;
        private int discount;

        private RealmList<Order> orderList;
}
