package com.martinchamarro.lazystorage.internal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.martinchamarro.lazystorage.internal.IdProvider;
import com.martinchamarro.lazystorage.internal.exception.DatabaseException;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.internal.exception.ObjectIdNotFoundException;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private static final String DATABASE_NAME = "lazy.db";
    private static final int DATABASE_VERSION = 1;

    private static final int ON_CONFLICT = SQLiteDatabase.CONFLICT_REPLACE;
    private static final String WHERE_CLASS = ObjectsTable.CLASS + "=?";
    private static final String WHERE_ID_AND_CLASS = ObjectsTable.ID + "=? AND " + ObjectsTable.CLASS + "=?";

    private IdProvider idProvider;
    private ObjectToValuesConverter objectConverter;
    private CursorToObjectConverter cursorConverter;

    public DatabaseImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.idProvider = new IdProvider();
        this.objectConverter = new ObjectToValuesConverter();
        this.cursorConverter = new CursorToObjectConverter();
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(ObjectsTable.CREATE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Empty
    }

    @Override public void save(Object object) throws LazyStorageException {
        save(getId(object), object);
    }

    @Override public void save(Object id, Object object) throws LazyStorageException {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.insertWithOnConflict(ObjectsTable.NAME, null, values(id, object), ON_CONFLICT);
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception saving object with id=" + id, e);
        }
    }

    @Override public <T> void saveAll(List<T> objects) throws LazyStorageException {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            for (T object : objects) {
                save(db, object);
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception saving list of objects", e);
        }
        db.endTransaction();
    }

    private <T> void save(SQLiteDatabase db, T object) throws LazyStorageException {
        db.insertWithOnConflict(ObjectsTable.NAME, null, values(getId(object), object), ON_CONFLICT);
    }

    @Override public <T> T load(Object id, Class<T> classOfT) throws LazyStorageException {
        try {
            T result = null;
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(ObjectsTable.NAME, null, WHERE_ID_AND_CLASS, idAndClassToArray(id, classOfT), null, null, null);
            if (cursor.moveToFirst()) {
                result = object(cursor, classOfT);
            }
            return result;
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception loading object with id=" + id + " and class=" + classOfT, e);
        }
    }

    @Override public <T> List<T> loadAll(Class<T> classOfT) throws LazyStorageException {
        try {
            List<T> result = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(ObjectsTable.NAME, null, WHERE_CLASS, classToArray(classOfT), null, null, null);
            while (cursor.moveToNext()) {
                result.add(object(cursor, classOfT));
            }
            return result;
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception loading all objects with class=" + classOfT, e);
        }
    }

    @Override public <T> void delete(Object id, Class<T> classOfT) throws LazyStorageException {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(ObjectsTable.NAME, WHERE_ID_AND_CLASS, idAndClassToArray(id, classOfT));
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception deleting object with id=" + id, e);
        }
    }

    @Override public <T> void deleteAll(Class<T> classOfT) throws LazyStorageException {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(ObjectsTable.NAME, WHERE_CLASS, classToArray(classOfT));
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception deleting objects of class=" + classOfT, e);
        }
    }

    @Override public <T> void deleteAll(List<T> objects) throws LazyStorageException {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            for(Object object : objects) {
                db.delete(ObjectsTable.NAME, WHERE_ID_AND_CLASS, idAndClassToArray(getId(object), object.getClass()));
            }
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception deleting list of objects", e);
        }
        db.endTransaction();
    }

    @Override public void invalidate() throws LazyStorageException {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(ObjectsTable.INVALIDATE);
        } catch (SQLiteException e) {
            throw new DatabaseException("Database exception invalidating", e);
        }
    }

    private String getId(Object object) throws ObjectIdNotFoundException {
        return idProvider.getId(object);
    }

    private ContentValues values(Object id, Object object) throws LazyStorageException {
        return objectConverter.convert(id, object);
    }

    private <T> T object(Cursor cursor, Class<T> classOfT) throws LazyStorageException {
        return cursorConverter.convert(cursor, classOfT);
    }

    private <T> String[] classToArray(Class<T> classOfT) {
        return new String[] { classOfT.getSimpleName() };
    }

    private <T> String[] idAndClassToArray(Object id, Class<T> classOfT) {
        return new String[] { String.valueOf(id), classOfT.getSimpleName() };
    }

}