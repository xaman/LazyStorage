package com.martinchamarro.lazystorage;

import android.content.Context;

import com.martinchamarro.lazystorage.internal.database.Database;
import com.martinchamarro.lazystorage.internal.database.DatabaseImpl;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.utils.Logger;

import java.util.List;

public final class LazyStorage {

    private static final String TAG = LazyStorage.class.getSimpleName();

    private Database database;

    public LazyStorage(Context context) {
        database = new DatabaseImpl(context);
    }


    public void save(Object object) {
        try {
            database.save(object);
        } catch (LazyStorageException e) {
            Logger.e(TAG, "Exception saving object.", e);
        }
    }

    public <T> void saveAll(List<T> objects) {
        try {
            database.saveAll(objects);
        } catch (LazyStorageException e) {
            Logger.e(TAG, "Exception saving objects.", e);
        }
    }

    public <T> T load(Object id, Class<T> classOfT) {
        T result = null;
        try {
            result = database.load(id, classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, "Exception loading object with id=" + id + " and class=" + classOfT, e);
        }
        return result;
    }

    public <T> List<T> loadAll(Class<T> classOfT) {
        List<T> result = null;
        try {
            result = database.loadAll(classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, "Exception trying to load all objects with class=" + classOfT, e);
        }
        return result;
    }

    public <T> void delete(Object id, Class<T> classOfT) {
        database.delete(id, classOfT);
    }

    public <T> void deleteAll(Class<T> classOfT) {
        database.deleteAll(classOfT);
    }

    public void invalidate() {
        database.invalidate();
    }
}
