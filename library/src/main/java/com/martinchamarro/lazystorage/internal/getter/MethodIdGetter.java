package com.martinchamarro.lazystorage.internal.getter;

import com.martinchamarro.lazystorage.utils.Logger;

import java.lang.reflect.Method;

public final class MethodIdGetter implements IdGetter {

    private static final String TAG = MethodIdGetter.class.getSimpleName();

    private String methodName;

    public MethodIdGetter(String methodName) {
        this.methodName = methodName;
    }

    @Override public Object getId(Object object) {
        Object id = null;
        try {
            Method method = object.getClass().getMethod(methodName);
            id = method.invoke(object);
        } catch (NoSuchMethodException e) {
            Logger.e(TAG, "The object doesn't have a " + methodName + " method", e);
        } catch (Exception e) {
            Logger.e(TAG, "Exception invoking " + methodName + " by reflection", e);
        }
        return id;
    }
}
