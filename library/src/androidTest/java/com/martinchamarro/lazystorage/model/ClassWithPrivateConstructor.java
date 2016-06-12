package com.martinchamarro.lazystorage.model;

public class ClassWithPrivateConstructor {

    private long id;

    private ClassWithPrivateConstructor(long id) {
        this.id = id;
    }

    public static ClassWithPrivateConstructor getInstance(long id) {
        return new ClassWithPrivateConstructor(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ClassWithPrivateConstructor)) return false;
        ClassWithPrivateConstructor other = (ClassWithPrivateConstructor) o;
        return other.getId() == id;
    }
}
