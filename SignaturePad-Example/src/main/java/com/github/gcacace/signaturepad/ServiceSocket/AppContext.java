package com.github.gcacace.signaturepad.ServiceSocket;

import android.app.Application;
import android.content.Context;


public class AppContext extends Application{
    private static Context context;


    public void onCreate(){
        super.onCreate();
        AppContext.context = getApplicationContext();
        //initializeSocket();
        /// enableAutoStart();

    }

    public static Context getAppContext() {
        return AppContext.context;
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
