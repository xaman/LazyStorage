package com.martinchamarro.lazystorage.model;

import com.martinchamarro.lazystorage.annotation.Id;

public class ClassWithAnnotatedField {

    @Id String field;

    public ClassWithAnnotatedField(String field) {
        this.field = field;
    }

}
