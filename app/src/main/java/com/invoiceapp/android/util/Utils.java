package com.invoiceapp.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.json.JSONObject;

public class Utils {

    private Context context;
    private SharedPreferences sharedPref;
     public static final String URL_WEBSOCKET = "ws://fazaasa.com:9005/server1.php";
    // public static final String URL_WEBSOCKET = "ws://151.236.57.83:9000/ch/server.php";
 //   public static final String URL_WEBSOCKET = "ws://192.168.1.23:9001/chat/ser .php";
    private static final String KEY_SHARED_PREF = "ANDROID_WEB_CHAT";
    private static final int KEY_MODE_PRIVATE = 0;
    private static final String KEY_SESSION_ID = "sessionId",
            FLAG_MESSAGE = "message";
    public static String TYPE = "type"; // 1
    public static String MESSAGE = "message"; //
    public static String READ = "read";

    public Utils(Context context) {
        this.context = context;
        sharedPref = this.context.getSharedPreferences(KEY_SHARED_PREF,
                KEY_MODE_PRIVATE);
    }

    public void storeSessionId(String sessionId) {
        Editor editor = sharedPref.edit();
        editor.putString(KEY_SESSION_ID, sessionId);
        editor.commit();
    }

    public String getSessionId() {
        return sharedPref.getString(KEY_SESSION_ID, null);
    }

    public String getSendMessageJSON(String message, String type, String sender_id, String receiver_id) {
        String json = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", message);
            jsonObject.put("type", type);
            jsonObject.put("sender_id", sender_id);
            jsonObject.put("reciever_id", receiver_id);
            json = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}
