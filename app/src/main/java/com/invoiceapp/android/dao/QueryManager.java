package com.invoiceapp.android.dao;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.invoiceapp.android.R;
import com.invoiceapp.android.listener.CallbackListener;
import com.invoiceapp.android.util.Constant;
import com.invoiceapp.android.util.Extension;
import com.invoiceapp.android.util.ValidationTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QueryManager {

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static volatile QueryManager instance = null;

    // private constructor
    private QueryManager() {
    }

    public static QueryManager getInstance() {
        if (instance == null) {
            synchronized (QueryManager.class) {
                // Double check
                if (instance == null) {
                    instance = new QueryManager();
                }
            }
        }
        return instance;
    }

    public void postRequest(Context context, String json, final CallbackListener callback) {
        if (Extension.getInstance().executeStrategy(context, "", ValidationTemplate.INTERNET)) {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(Constant.URL)
                    .post(body).cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResult(e, "");
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResult(null, result);
                        }
                    });
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    public static String getMimeType(String url) {
      /*  String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }*/

        String exten = "";

        int i = url.lastIndexOf('.');
        if (i > 0) {
            exten = url.substring(i + 1);
        }
        return exten;
    }
}
