package com.martinchamarro.lazystorage.internal.getter;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.model.ClassWithIdField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class FieldIdGetterTest {

    private static final String ID_FIELD_NAME = "id";
    private static final long ANY_LONG_ID = 612345678;

    private FieldIdGetter getter;

    @Before public void setUp() {
        getter = new FieldIdGetter(ID_FIELD_NAME);
    }

    @Test public void testGetIdFromField() {
        Object id = getter.getId(new ClassWithIdField(ANY_LONG_ID));
        assertEquals(id, ANY_LONG_ID);
    }

    @Test public void testGetIdFromClassWithoutMethod() {
        assertNull(getter.getId(new Object()));
    }

}
