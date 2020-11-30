package com.louis.shippingcalculator.util;

public class DbUtil {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydb";


    public class Address {
        public static final String TABLE_NAME = "address";

        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_CITY = "city";
        public static final String KEY_PROV = "province";
        public static final String KEY_POSTALCODE = "postalCode";
        public static final String KEY_COUNTRY = "country";
    }

    public class Box {
        public static final String TABLE_NAME = "box";

        public static final String KEY_ID = "id";
        public static final String KEY_NAME = "name";
        public static final String KEY_WIDTH = "width";
        public static final String KEY_HEIGHT = "height";
        public static final String KEY_DEPTH = "depth";
    }
}
