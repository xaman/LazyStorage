package com.martinchamarro.lazystorage.internal.getter;

import com.martinchamarro.lazystorage.utils.Logger;

import java.lang.reflect.Field;

public final class FieldIdGetter implements IdGetter {

    private static final String TAG = FieldIdGetter.class.getSimpleName();

    private String fieldName;

    public FieldIdGetter(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override public Object getId(Object object) {
        Object id = null;
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            id = field.get(object);
        } catch (NoSuchFieldException e) {
            Logger.e(TAG, "The object doesn't have " + fieldName + " field", e);
        } catch (IllegalAccessException e) {
            Logger.e(TAG, "Exception trying to access " + fieldName + " field", e);
        }
        return id;
    }
}
