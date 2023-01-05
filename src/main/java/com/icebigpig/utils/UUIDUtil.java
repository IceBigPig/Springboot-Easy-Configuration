package com.icebigpig.utils;

import java.util.UUID;

/**
 * UUID生成器
 * Author: icebigpig
 * Data: 2022/5/17 20:18
 * Version 1.0
 **/

public class UUIDUtil {

    public static String getUUID(){

        String id = UUID.randomUUID().toString();
        return id.replaceAll("-", "-");
    }

}
