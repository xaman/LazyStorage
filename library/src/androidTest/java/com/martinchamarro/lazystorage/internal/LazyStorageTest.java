package com.martinchamarro.lazystorage.internal;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.martinchamarro.lazystorage.LazyStorage;
import com.martinchamarro.lazystorage.model.Address;
import com.martinchamarro.lazystorage.model.Car;
import com.martinchamarro.lazystorage.model.Client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LazyStorageTest {

    private static final int ANY_INT_ID = 1234;
    private static final String ANY_STRING_ID = "1234";
    private static final String ANY_STRING_VALUE = "QWERTY";

    private LazyStorage lazy;

    @Before public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        lazy = new LazyStorage(context);
    }

    @Test public void testSaveAndLoad() {
        Object source = givenOneObject();
        lazy.save(source);
        Object result = lazy.load(ANY_STRING_ID, source.getClass());
        assertEquals(source, result);
    }

    @Test public void testSaveWithGivenIdAndLoad() {
        Object source = givenOneObject();
        lazy.save(ANY_INT_ID, source);
        Object result = lazy.load(ANY_INT_ID, source.getClass());
        assertEquals(source, result);
    }

    @Test public void testInsertLoadAndDelete() {
        Object source = givenOneObject();
        lazy.save(source);
        Object loaded = lazy.load(ANY_STRING_ID, source.getClass());
        lazy.delete(ANY_STRING_ID, source.getClass());
        Object loadAfterDelete = lazy.load(ANY_STRING_ID, source.getClass());
        assertEquals(source, loaded);
        assertNull(loadAfterDelete);
    }

    @Test public void testSaveAndUpdate() {
        Car source = givenOneObject();
        lazy.save(source);
        source.setBrand(ANY_STRING_VALUE);
        lazy.save(source);
        Car result = lazy.load(ANY_STRING_ID, Car.class);
        assertEquals(source.getBrand(), result.getBrand());
    }

    @Test public void testSaveDifferentObjectsWithTheSameId() {
        Client client = givenAClient();
        client.setId(ANY_STRING_ID);
        Address address = givenAnAddress();
        address.setId(ANY_INT_ID);
        Car car = givenOneObject();
        car.setRegistration(ANY_STRING_ID);
        lazy.save(client);
        lazy.save(address);
        lazy.save(car);
        Client loadedClient = lazy.load(ANY_STRING_ID, Client.class);
        Address loadedAddress = lazy.load(ANY_STRING_ID, Address.class);
        Car loadedCar = lazy.load(ANY_STRING_ID, Car.class);
        assertEquals(client, loadedClient);
        assertEquals(address, loadedAddress);
        assertEquals(car, loadedCar);
    }

    @Test @MediumTest public void testSaveAThousandAndLoad() {
        List<Car> source = givenAList(1000);
        lazy.saveAll(source);
        Object result = lazy.loadAll(Car.class);
        assertEquals(source, result);
    }

    @Test @MediumTest public void testSaveAllAndDelete() {
        List<Car> source = givenAList(10);
        lazy.saveAll(source);
        List<Car> result = lazy.loadAll(Car.class);
        assertEquals(source, result);
        lazy.deleteAll(Car.class);
        List<Car> loadAfterDelete = lazy.loadAll(Car.class);
        assertTrue(loadAfterDelete.isEmpty());
    }

    @After public void restore() {
        lazy.invalidate();
    }

    private Car givenOneObject() {
        Car car = new Car();
        car.setRegistration(ANY_STRING_ID);
        car.setBrand("Ferrari");
        car.setModel("LaFerrari");
        car.setSeatsNumber(2);
        car.setMaxSpeed(350.0f);
        return car;
    }

    private List<Car> givenAList(int number) {
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < number; i++) {
            cars.add(givenAnObjectFromId(i));
        }
        return cars;
    }

    private Car givenAnObjectFromId(int id) {
        Car car = new Car();
        car.setRegistration(id + "");
        car.setBrand("Brand " + id);
        car.setModel("Model " + id);
        car.setSeatsNumber(4);
        car.setMaxSpeed(150.0f);
        return car;
    }

    private Client givenAClient() {
        Client client = new Client();
        client.setPassport("123456789ABC");
        client.setName("Walter");
        client.setSurname("Kovacs");
        client.setAge(30);
        return client;
    }

    private Address givenAnAddress() {
        Address address = new Address();
        address.setId(1970);
        address.setStreet("Wall St.");
        address.setNumber(213);
        address.setFloor(2);
        address.setCity("New York");
        address.setCountry("USA");
        return address;
    }

}
