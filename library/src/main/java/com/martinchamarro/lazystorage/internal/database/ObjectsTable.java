package com.martinchamarro.lazystorage.internal.database;

public final class ObjectsTable {

    public static final String NAME = "objects";

    public static final String ID = "id";
    public static final String CLASS = "class";
    public static final String VALUE = "value";
    public static final String LAST_UPDATE = "last_update";

    public static final String CREATE = "CREATE TABLE IF NOT EXISTS " + NAME + " ("
            + ID + " TEXT,"
            + CLASS + " TEXT,"
            + VALUE + " TEXT,"
            + LAST_UPDATE + " INTEGER,"
            + " PRIMARY KEY (" + ID + ", " + CLASS + ")"
            + ");";

    public static final String INVALIDATE = "DELETE FROM " + NAME + ";";

}
