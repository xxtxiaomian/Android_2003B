package com.example.demo_2003.factory2;

import com.example.demo_2003.factory.Phone;

public class HuaWeiPhoneFactory implements CreatePhoneFactory {
    @Override
    public Phone createPhone() {
        return new HuaWeiPhone();
    }
}
