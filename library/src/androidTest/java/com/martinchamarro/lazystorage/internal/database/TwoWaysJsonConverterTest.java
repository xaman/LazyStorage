package com.martinchamarro.lazystorage.internal.database;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.model.Car;
import com.martinchamarro.lazystorage.model.ClassWithPrivateConstructor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class TwoWaysJsonConverterTest {

    private static final long ANY_LONG_ID = 666L;

    private TwoWaysJsonConverter converter;

    @Before public void setUp() {
        converter = new TwoWaysJsonConverter();
    }

    @Test public void testTwoWaysConversion() {
        Object source = givenAnObject();
        String json = null;
        Object result = null;
        try {
            json = converter.convert(source);
            result = converter.convert(json, source.getClass());
        } catch (Exception e) {}
        assertNotNull(json);
        assertEquals(source, result);
    }

    @Test public void testConversionOfClassWithPrivateConstructor() {
        Object source = ClassWithPrivateConstructor.getInstance(ANY_LONG_ID);
        String json = null;
        Object result = null;
        try {
            json = converter.convert(source);
            result = converter.convert(json, source.getClass());
        } catch (LazyStorageException e) {}
        assertNotNull(json);
        assertEquals(source, result);
    }

    private Object givenAnObject() {
        Car car = new Car();
        car.setRegistration("1234FTW");
        car.setBrand("Ferrari");
        car.setModel("LaFerrari");
        car.setSeatsNumber(2);
        car.setMaxSpeed(350.0f);
        return car;
    }

}
