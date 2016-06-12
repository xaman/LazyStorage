package com.martinchamarro.lazystorage;

import android.content.Context;

import com.martinchamarro.lazystorage.internal.database.Database;
import com.martinchamarro.lazystorage.internal.database.DatabaseImpl;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.utils.Logger;

import java.util.List;

/**
 *
 * This class is the point of access of the library.
 * All the methods of the class work synchronously, so it's necessary to call them
 * in background using a Thread, an AsyncTask or other thread implementation.
 *
 */
public final class LazyStorage {

    private static final String TAG = LazyStorage.class.getSimpleName();

    private Database database;

    public LazyStorage(Context context) {
        database = new DatabaseImpl(context);
    }

    /**
     * Saves an object to the database.
     * Uses an IdProvider to get the object id by reflection.
     *
     * @param object to save
     */
    public void save(Object object) {
        try {
            database.save(object);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Saves an object to the database.
     * Doesn't use reflection to get the id of the object.
     *
     * @param id of the object to save
     * @param object to save in the database
     */
    public void save(Object id, Object object) {
        try {
            database.save(id, object);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Saves a list of objects to the database.
     *
     * @param objects to save
     * @param <T>
     */
    public <T> void saveAll(List<T> objects) {
        try {
            database.saveAll(objects);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Loads an object from the database.
     *
     * @param id of the object to load
     * @param classOfT of the object to load
     * @param <T>
     * @return the object to load or null if the object doesn't exist or
     * there is some problem converting the saved data to an object.
     */
    public <T> T load(Object id, Class<T> classOfT) {
        T result = null;
        try {
            result = database.load(id, classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
        return result;
    }

    /**
     * Loads all the objects of the given class.
     *
     * @param classOfT is the class of the objects to load
     * @param <T>
     * @return a list of objects of the given class or an empty list if
     * there aren't objects of this class or there is a problem loading them.
     */
    public <T> List<T> loadAll(Class<T> classOfT) {
        List<T> result = null;
        try {
            result = database.loadAll(classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
        return result;
    }

    /**
     * Deletes from the database the object with the given id and the given class.
     *
     * @param id of the object to delete
     * @param classOfT of the object to delete
     * @param <T>
     */
    public <T> void delete(Object id, Class<T> classOfT) {
        try {
            database.delete(id, classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Deletes all the existing objects of the given class.
     *
     * @param classOfT of the all the objects to delete
     * @param <T>
     */
    public <T> void deleteAll(Class<T> classOfT) {
        try {
            database.deleteAll(classOfT);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Deletes all the given objects from the database.
     *
     * @param objects is the list of objects to delete
     * @param <T>
     */
    public <T> void deleteAll(List<T> objects) {
        try {
            database.deleteAll(objects);
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Deletes all the content of the database.
     */
    public void invalidate() {
        try {
            database.invalidate();
        } catch (LazyStorageException e) {
            Logger.e(TAG, e);
        }
    }

    /**
     * Enables the log of the library that is disabled by default.
     */
    public void enableLogging() {
        Logger.enable();
    }
}
