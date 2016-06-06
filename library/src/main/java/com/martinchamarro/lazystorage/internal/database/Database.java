package com.martinchamarro.lazystorage.internal.database;

import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;

import java.util.List;

public interface Database {

    void save(Object object) throws LazyStorageException;

    void save(Object id, Object object) throws LazyStorageException;

    <T> void saveAll(List<T> objects) throws LazyStorageException;

    <T> T load(Object id, Class<T> classOfT) throws LazyStorageException;

    <T> List<T> loadAll(Class<T> classOfT) throws LazyStorageException;

    <T> void delete(Object id, Class<T> classOfT);

    <T> void deleteAll(Class<T> classOfT);

    void invalidate();

}
