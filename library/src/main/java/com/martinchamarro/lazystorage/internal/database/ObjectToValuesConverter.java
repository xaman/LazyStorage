package com.martinchamarro.lazystorage.internal.database;

import android.content.ContentValues;

import com.martinchamarro.lazystorage.internal.IdProvider;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.internal.exception.ObjectIdNotFoundException;

public final class ObjectToValuesConverter {

    private IdProvider idProvider;
    private TwoWaysJsonConverter jsonConverter;

    public ObjectToValuesConverter() {
        idProvider = new IdProvider();
        jsonConverter = new TwoWaysJsonConverter();
    }

    public ContentValues convert(Object object) throws LazyStorageException {
        ContentValues values = new ContentValues();
        values.put(ObjectsTable.ID, getId(object));
        values.put(ObjectsTable.VALUE, getValue(object));
        values.put(ObjectsTable.CLASS, getClass(object));
        values.put(ObjectsTable.LAST_UPDATE, getLastUpdate());
        return values;
    }

    private String getId(Object object) throws ObjectIdNotFoundException {
        return idProvider.getId(object);
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
