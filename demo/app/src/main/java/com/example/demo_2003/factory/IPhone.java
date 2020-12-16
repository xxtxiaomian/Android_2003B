package com.example.demo_2003.factory;

import android.util.Log;

public class IPhone implements Phone {

    private static final String TAG = IPhone.class.getSimpleName();

    @Override
    public void make() {
        Log.e(TAG, "make: " + "制造苹果手机");
    }
}
