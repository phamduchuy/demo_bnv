package com.github.gcacace.signaturepad;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.github.gcacace.signaturepad.ServiceSocket.AppSocketListener;

public class AppSignature extends Application
{
    private static Context context;
        @Override
        public void onCreate() {
            super.onCreate();
            AppSignature.context = getApplicationContext();
            FileUtils.createApplicationFolder();

        }

        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(this);
        }





    public static Context getAppContext() {
        return AppSignature.context;
    }

    public void initializeSocket(){
        AppSocketListener.getInstance().initialize();
    }

    public void destroySocketListener(){
        AppSocketListener.getInstance().destroy();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        destroySocketListener();
    }

}
