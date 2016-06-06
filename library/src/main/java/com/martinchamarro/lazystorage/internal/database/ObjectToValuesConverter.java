package com.martinchamarro.lazystorage.internal.database;

import android.content.ContentValues;

import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;

public final class ObjectToValuesConverter {

    private TwoWaysJsonConverter jsonConverter;

    public ObjectToValuesConverter() {
        jsonConverter = new TwoWaysJsonConverter();
    }

    public ContentValues convert(Object id, Object object) throws LazyStorageException {
        ContentValues values = new ContentValues();
        values.put(ObjectsTable.ID, castId(id));
        values.put(ObjectsTable.VALUE, getValue(object));
        values.put(ObjectsTable.CLASS, getClass(object));
        values.put(ObjectsTable.LAST_UPDATE, getLastUpdate());
        return values;
    }

    private String castId(Object id) {
        return String.valueOf(id);
    }

    private String getValue(Object object) throws LazyStorageException {
        return jsonConverter.convert(object);
    }

    private String getClass(Object object) {
        return object.getClass().getSimpleName();
    }

    private long getLastUpdate() {
        return System.currentTimeMillis();
    }
}
