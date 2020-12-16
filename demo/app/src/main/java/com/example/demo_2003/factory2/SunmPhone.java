package com.example.demo_2003.factory2;

import android.util.Log;

import com.example.demo_2003.factory.Phone;

public class SunmPhone implements Phone {
    private static final String TAG = SunmPhone.class.getSimpleName();

    @Override
    public void make() {
        Log.e(TAG, "make: " + "制造三星手机");
    }
}
