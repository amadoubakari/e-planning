package com.flys.eplanning.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by User on 14/05/2018.
 */

public class FlysApplicationContext extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    @Override
    public void attachBaseContext(Context base) {
        //MultiDex.install(base);
        super.attachBaseContext(base);
    }
    public static Context getContext() {
        return mContext;
    }
}
