package com.example.demo_2003.factory;

import android.util.Log;

public class MiPhone implements Phone {

    private static final String TAG = MiPhone.class.getSimpleName();

    @Override
    public void make() {
        Log.e(TAG, "make: " + "制造小米手机");
    }
}
