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
            Logger.e(TAG, e);
        }
    }

    public void save(Object id, Object object) {
        try {
            database.save(id, object);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    public <T> void saveAll(List<T> objects) {
        try {
            database.saveAll(objects);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    public <T> T load(Object id, Class<T> classOfT) {
        T result = null;
        try {
            result = database.load(id, classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
        return result;
    }

    public <T> List<T> loadAll(Class<T> classOfT) {
        List<T> result = null;
        try {
            result = database.loadAll(classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
        return result;
    }

    public <T> void delete(Object id, Class<T> classOfT) {
        try {
            database.delete(id, classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    public <T> void deleteAll(Class<T> classOfT) {
        try {
            database.deleteAll(classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    public <T> void deleteAll(List<T> objects) {
        try {
            database.deleteAll(objects);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    public void invalidate() {
        try {
            database.invalidate();
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }
}
