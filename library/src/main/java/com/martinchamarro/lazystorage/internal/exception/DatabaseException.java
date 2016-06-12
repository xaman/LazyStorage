package com.martinchamarro.lazystorage.internal.exception;

public class DatabaseException extends LazyStorageException {

    public DatabaseException() {
        super();
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String detailMessage) {
        super(detailMessage);
    }

    public DatabaseException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }

}
