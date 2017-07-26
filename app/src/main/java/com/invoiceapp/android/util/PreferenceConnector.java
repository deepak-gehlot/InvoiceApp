package com.invoiceapp.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@SuppressWarnings("deprecation")
public class PreferenceConnector {
    public static final String PREF_NAME = "orangeinvoice";
    public static final int MODE = Context.MODE_PRIVATE;
    public static final String USER_DATA = "user_data";
    public static final String USER_ID = "user_id";
    public static final String IS_FROM_WIZARD = "is_from_wizard";
    public static final String TYPE_OF_BIKE = "typeOfBike";
    public static final String LOGIN_EMAIL = "LOGIN_EMAIL";
    public static final String PROFILE_PIC = "profile_pic";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String FROM = "from";
    public static final String DESCRIPTION = "description";
    public static final String FACEBOOK_URL = "facebook_url";
    public static final String TWITTER_URL = "twitter_url";
    public static final String INSTAGRAM_URL = "instagram_url";
    public static final String GENDER = "gender";
    public static final String NUMBER = "number";
    public static final String DOB = "dob";
    public static final String IS_LOGIN = "IS_LOGIN";
    public static final String IS_RETAILER = "is_retailer";
    public static final String DEVICE_HEIGHT = "DEVICE_HEIGHT";
    public static final String DEVICE_WIDTH = "DEVICE_WIDTH";
    public static final String NOTIFICATION_COUNT = "notification_count";
    public static final String MSG_COUNT = "msg_count";
    public static final String IS_IS_CHAT = "is_in_chat";
    public static final String CHAT_WITH_ID = "chat_with_id";
    public static final String DEVICE_TOKEN = "device_token";


    public static void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public static boolean readBoolean(Context context, String key, boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }

    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public static float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public static void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public static long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);

    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void remove(Context context, String key) {
        getEditor(context).remove(key);

    }

    public static void clear(Context context) {
        getEditor(context).clear().commit();
        getEditor(context).clear().commit();

    }
}
