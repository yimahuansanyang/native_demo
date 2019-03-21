package com.example.retrolfitdemo.utils;

import android.os.Build;

public class DeviceUtils {
    /**
     * 获取设备厂商
     * <p>如 Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }
}
