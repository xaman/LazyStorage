package com.martinchamarro.lazystorage.internal.exception;

public class LazyStorageException extends Exception {

    public LazyStorageException() {
        super();
    }

    public LazyStorageException(Throwable cause) {
        super(cause);
    }

    public LazyStorageException(String detailMessage) {
        super(detailMessage);
    }

    public LazyStorageException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }

}
