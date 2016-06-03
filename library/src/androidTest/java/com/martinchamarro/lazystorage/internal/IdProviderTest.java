package com.martinchamarro.lazystorage.internal;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.internal.IdProvider;
import com.martinchamarro.lazystorage.internal.exception.ObjectIdNotFoundException;
import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.model.ClassWithAnnotatedField;
import com.martinchamarro.lazystorage.model.ClassWithGetIdMethod;
import com.martinchamarro.lazystorage.model.ClassWithIdField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class IdProviderTest {

    private static final String ANY_STRING_ID = "interstellar";
    private static final long ANY_LONG_ID = 314159265359L;
    private static final int ANY_INT_ID = 42;

    private IdProvider provider;

    @Before public void setUp() {
        provider = new IdProvider();
    }

    @Test public void testGetIdFromAnnotation() {
        String id = null;
        try {
            id = provider.getId(new ClassWithAnnotatedField(ANY_STRING_ID));
        } catch (ObjectIdNotFoundException e) {}
        assertEquals(id, ANY_STRING_ID);
    }

    @Test public void testGetIdFromField() {
        String id = null;
        try {
            id = provider.getId(new ClassWithIdField(ANY_LONG_ID));
        } catch (LazyStorageException e) {}
        assertEquals(id, String.valueOf(ANY_LONG_ID));
    }

    @Test public void testGetIdFromMethod() {
        String id = null;
        try {
            id = provider.getId(new ClassWithGetIdMethod(ANY_INT_ID));
        } catch (ObjectIdNotFoundException e) {}
        assertEquals(id, String.valueOf(ANY_INT_ID));
    }

}
