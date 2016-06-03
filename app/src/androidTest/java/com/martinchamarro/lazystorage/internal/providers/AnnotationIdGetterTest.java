package com.martinchamarro.lazystorage.internal.providers;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.annotation.Id;
import com.martinchamarro.lazystorage.model.ClassWithAnnotatedField;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class AnnotationIdGetterTest {

    private static final String ANY_STRING_ID = "12345678A";

    private AnnotationIdGetter getter;

    @Before public void setUp() {
        getter = new AnnotationIdGetter(Id.class);
    }

    @Test public void testGetIdFromAnnotation() {
        Object id = getter.getId(new ClassWithAnnotatedField(ANY_STRING_ID));
        assertEquals(id, ANY_STRING_ID);
    }

    @Test public void testGetIdFromClassWithoutAnnotation() {
        assertNull(getter.getId(new Object()));
    }

}
