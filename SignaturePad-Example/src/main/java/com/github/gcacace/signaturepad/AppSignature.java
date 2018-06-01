package com.github.gcacace.signaturepad;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
public class AppSignature extends Application
{

        @Override
        public void onCreate() {
            super.onCreate();
            FileUtils.createApplicationFolder();

        }

        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(this);
        }
}
