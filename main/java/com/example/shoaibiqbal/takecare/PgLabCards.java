package com.example.shoaibiqbal.takecare;

import android.app.Activity;

/**
 * Created by BoboMan2 on 16/04/2016.
 */
public class PgLabCards extends PgPharmacyCards {

    @Override
    protected void fetchPharmacies() {
        allPharmacies.add(new Pharmacy("Ibn Seena Laboratory", "Gulistan-e-Jauhar"));
        allPharmacies.add(new Pharmacy("Agha Khan Laboratory", "Gulistan-e-Jauhar"));
        allPharmacies.add(new Pharmacy("Agha Khan Laboratory", "Gulshan"));
        allPharmacies.add(new Pharmacy("Agha Khan Laboratory", "Nazimabad"));
        allPharmacies.add(new Pharmacy("Agha Khan Laboratory", "Karimabad"));
        allPharmacies.add(new Pharmacy("Agha Khan Laboratory", "DHA"));
    }
}
