package com.example.demo_2003.factory;

/**
 * 工厂类
 */
public class PhoneFactory {

    public static Phone create(String type) {
        if ("MiPhone".equals(type)) {
            return new MiPhone();
        } else if ("IPhone".equals(type)) {
            return new IPhone();
        }
        return null;
    }



}
