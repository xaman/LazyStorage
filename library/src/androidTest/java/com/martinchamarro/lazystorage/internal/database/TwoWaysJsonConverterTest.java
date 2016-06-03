package com.martinchamarro.lazystorage.internal.database;

import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.internal.exception.LazyStorageException;
import com.martinchamarro.lazystorage.model.Car;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class TwoWaysJsonConverterTest {

    private TwoWaysJsonConverter converter;

    @Before public void setUp() {
        converter = new TwoWaysJsonConverter();
    }

    @Test public void testTwoWaysConversion() {
        Object source = givenAnObject();
        String json = null;
        try {
            json = converter.convert(source);
        } catch (LazyStorageException e) {}
        assertNotNull(json);
        Object result = null;
        try {
            result = converter.convert(json, source.getClass());
        } catch (LazyStorageException e) {}
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
