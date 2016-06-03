package com.martinchamarro.lazystorage.utils;

import android.util.Log;

public final class Logger {

    private Logger() {
        // Empty
    }

    public static void d(String tag, String message) {
        Log.d("[" + tag + "][D] ", message);
    }

    public static void w(String tag, String message) {
        Log.w("[" + tag + "][W] ", message);
    }

    public static void w(String tag, String message, Throwable throwable) {
        Log.w("[" + tag + "][W] ", message, throwable);
    }

    public static void e(String tag, String message) {
        Log.e("[" + tag + "][E] ", message);
    }

    public static void e(String tag, String message, Throwable throwable) {
        Log.e("[" + tag + "][E] ", message, throwable);
    }
}
