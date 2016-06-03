package com.martinchamarro.lazystorage.internal.providers;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.model.ClassWithGetIdMethod;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class MethodIdGetterTest {

    private static final String GET_ID_METHOD = "getId";
    private static final int ANY_INT_ID = 528491;

    private MethodIdGetter getter;

    @Before public void setUp() {
        getter = new MethodIdGetter(GET_ID_METHOD);
    }

    @Test public void testGetIdFromMethod() {
        Object id = getter.getId(new ClassWithGetIdMethod(ANY_INT_ID));
        assertEquals(id, ANY_INT_ID);
    }

    @Test public void testGetIdFromObjectWithoutMethod() {
        assertNull(getter.getId(new Object()));
    }

}
