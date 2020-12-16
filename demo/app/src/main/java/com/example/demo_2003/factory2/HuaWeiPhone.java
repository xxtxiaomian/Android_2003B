package com.example.demo_2003.factory2;

import android.util.Log;

import com.example.demo_2003.factory.Phone;

public class HuaWeiPhone implements Phone {
    private static final String TAG = HuaWeiPhone.class.getSimpleName();

    @Override
    public void make() {
        Log.e(TAG, "make: " + "制造华为手机");
    }
}
