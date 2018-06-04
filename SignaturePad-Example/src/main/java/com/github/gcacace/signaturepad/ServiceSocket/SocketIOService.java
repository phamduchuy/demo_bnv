package com.github.gcacace.signaturepad.ServiceSocket;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class SocketIOService extends Service {
    private  SocketListener socketListener;
    private Boolean appConnectedToService;
    private  Socket mSocket;
    private boolean serviceBinded = false;
    private final LocalBinder mBinder = new LocalBinder();

    public void setAppConnectedToService(Boolean appConnectedToService) {
        this.appConnectedToService = appConnectedToService;
    }

    public void setSocketListener(SocketListener socketListener) {
        this.socketListener = socketListener;
    }

    public class LocalBinder extends Binder {
       public SocketIOService getService(){
            return SocketIOService.this;
        }
    }

    public void setServiceBinded(boolean serviceBinded) {
        this.serviceBinded = serviceBinded;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeSocket();
        addSocketHandlers();
        Log.e("SERVICE ONCREATE","chay roi");
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        closeSocketSession();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return serviceBinded;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    private void initializeSocket() {
        try{
            IO.Options options = new IO.Options();
            options.forceNew = true;
            mSocket = IO.socket("http://113.190.234.46:3001",options);
        }
        catch (Exception e){
            Log.e("Error", "Exception in socket creation");
            throw new RuntimeException(e);
        }
    }
    private void closeSocketSession(){
        mSocket.disconnect();
        mSocket.off();
    }
    private void addSocketHandlers(){

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent = new Intent(SocketEventConstants.socketConnection);
                intent.putExtra("connectionStatus", true);
                broadcastEvent(intent);
            }
        });

        mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent = new Intent(SocketEventConstants.socketConnection);
                intent.putExtra("connectionStatus", false);
                broadcastEvent(intent);
            }
        });


        mSocket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent = new Intent(SocketEventConstants.connectionFailure);
                broadcastEvent(intent);
            }
        });

        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent = new Intent(SocketEventConstants.connectionFailure);
                broadcastEvent(intent);
            }
        });

        mSocket.connect();
    }

    public void removeMessageHandler() {
        mSocket.off(SocketEventConstants.newMessage);
    }
    public void emit(String event, Object[] args, Ack ack){
        mSocket.emit(event, args, ack);
    }
    public void emit (String event, Object... args) {
        try {
            mSocket.emit(event, args,null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addOnHandler(String event, Emitter.Listener listener){
            mSocket.on(event, listener);
    }
    public void connect(){
        mSocket.connect();
    }
    public void disconnect(){
        mSocket.disconnect();
    }
    public void restartSocket(){
        mSocket.off();
        mSocket.disconnect();
        try{
            IO.Options options = new IO.Options();
            options.forceNew = true;
            mSocket = IO.socket("http://113.190.234.46:3001");
        }
        catch (Exception e){
            Log.e("Error", "Exception in socket creation");
            throw new RuntimeException(e);
        }
        addSocketHandlers();
    }
    public void off(String event){
        mSocket.off(event);
    }
    private void broadcastEvent(Intent intent){
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    public boolean isSocketConnected(){
        if (mSocket == null){
            return false;
        }
        return mSocket.connected();
    }
    public boolean isForeground(String myPackage) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfo = manager.getRunningTasks(1);
        ComponentName componentInfo = runningTaskInfo.get(0).topActivity;
        return componentInfo.getPackageName().equals(myPackage);
    }


}
