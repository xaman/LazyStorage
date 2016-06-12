package com.martinchamarro.lazystorage.utils;

import android.util.Log;

public final class Logger {

    private static final String TAG = "LazyStorage";

    private static boolean enabled = false;

    private Logger() {
        // Empty
    }

    public static void d(String tag, String message) {
        if (enabled) Log.d(TAG, "[" + tag + "] - " + message);
    }

    public static void w(String tag, String message) {
        if (enabled) Log.w(TAG, "[" + tag + "] - " + message);
    }

    public static void w(String tag, String message, Throwable throwable) {
        if (enabled) Log.w(TAG, "[" + tag + "] - " + message + " - " + throwable.getMessage());
    }

    public static void e(String tag, String message) {
        if (enabled) Log.e(TAG, "[" + tag + "] - " + message);
    }

    public static void e(String tag, Throwable throwable) {
        if (enabled) Log.e(TAG, "[" + tag + "] - " + throwable.getMessage());
    }

    public static void e(String tag, String message, Throwable throwable) {
        if (enabled) Log.e(TAG, "[" + tag + "] - " + message + " - " + throwable.getMessage());
    }

    public static void enable() {
        enabled = true;
    }
}
