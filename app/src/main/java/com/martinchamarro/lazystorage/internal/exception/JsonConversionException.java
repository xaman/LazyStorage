package com.martinchamarro.lazystorage.internal.exception;

public class JsonConversionException extends LazyStorageException {

    public JsonConversionException(String detailMessage) {
        super(detailMessage);
    }

    public JsonConversionException(String detailMessage, Throwable e) {
        super(detailMessage, e);
    }

}
