package com.martinchamarro.lazystorage.internal.database;

import android.database.Cursor;

import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;

public final class CursorToObjectConverter {

    private TwoWaysJsonConverter jsonConverter;

    public CursorToObjectConverter() {
        this.jsonConverter = new TwoWaysJsonConverter();
    }

    public <T> T convert(Cursor cursor, Class<T> classOfT) throws LazyStorageException {
        String value = cursor.getString(cursor.getColumnIndex(ObjectsTable.VALUE));
        return jsonConverter.convert(value, classOfT);
    }

}
