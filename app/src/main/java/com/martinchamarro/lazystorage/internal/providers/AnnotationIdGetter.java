package com.martinchamarro.lazystorage.internal.providers;

import com.martinchamarro.lazystorage.utils.Logger;

import java.lang.reflect.Field;

public final class AnnotationIdGetter implements IdGetter {

    private static final String TAG = AnnotationIdGetter.class.getSimpleName();

    private Class classOfAnnotation;

    public AnnotationIdGetter(Class classOfAnnotation) {
        this.classOfAnnotation = classOfAnnotation;
    }

    @Override public Object getId(Object object) {
        Object id = null;
        try {
            for (Field field : getFields(object)) {
                if (hasAnnotation(field)) {
                    field.setAccessible(true);
                    id = field.get(object);
                }
            }
        } catch (IllegalAccessException e) {
            Logger.e(TAG, "Exception trying to get object id from " + classOfAnnotation.getSimpleName() + " annotation", e);
        }
        return id;
    }

    private Field[] getFields(Object object) {
        return object.getClass().getDeclaredFields();
    }

    private boolean hasAnnotation(Field field) {
        return field.isAnnotationPresent(classOfAnnotation);
    }
}
