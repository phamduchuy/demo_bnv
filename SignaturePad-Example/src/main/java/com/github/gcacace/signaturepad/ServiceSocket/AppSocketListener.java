package com.github.gcacace.signaturepad.ServiceSocket;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.github.gcacace.signaturepad.AppSignature;

import io.socket.client.Ack;
import io.socket.emitter.Emitter;


public class AppSocketListener implements SocketListener
{

    private static AppSocketListener sharedInstance;
    private SocketIOService socketServiceInterface;
    public SocketListener activeSocketListener;

    public void setActiveSocketListener(SocketListener activeSocketListener)
    {
        this.activeSocketListener = activeSocketListener;
        if (socketServiceInterface != null && socketServiceInterface.isSocketConnected())
        {
            onSocketConnected();
        }
    }

    public static AppSocketListener getInstance(){
        if (sharedInstance==null){
            sharedInstance = new AppSocketListener();
        }
        return sharedInstance;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            socketServiceInterface = ((SocketIOService.LocalBinder)service).getService();
            socketServiceInterface.setServiceBinded(true);
            socketServiceInterface.setSocketListener(sharedInstance);
            if (socketServiceInterface.isSocketConnected()){
                onSocketConnected();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            socketServiceInterface.setServiceBinded(false);
            socketServiceInterface=null;
            onSocketDisconnected();
        }
    };


    public void initialize(){
        Intent intent = new Intent(AppSignature.getAppContext(), SocketIOService.class);

        AppSignature.getAppContext().startService(intent);


        AppSignature.getAppContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(AppSignature.getAppContext()).
                registerReceiver(socketConnectionReceiver, new IntentFilter(SocketEventConstants.
                        socketConnection));
        LocalBroadcastManager.getInstance(AppSignature.getAppContext()).
                registerReceiver(connectionFailureReceiver, new IntentFilter(SocketEventConstants.
                        connectionFailure));
        LocalBroadcastManager.getInstance(AppSignature.getAppContext()).
                registerReceiver(newMessageReceiver, new IntentFilter(SocketEventConstants.
                        newMessage));
    }

    private BroadcastReceiver socketConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           boolean connected = intent.getBooleanExtra("connectionStatus",false);
            if (connected){
                Log.i("AppSocketListener","Socket connected");
                onSocketConnected();
            }
            else{
                onSocketDisconnected();
            }
        }
    };

    private BroadcastReceiver connectionFailureReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast toast = Toast.
                    makeText(AppSignature.getAppContext(), "Please check your network connection",
                            Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    private BroadcastReceiver newMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String userName = intent.getStringExtra("username");
            String message = intent.getStringExtra("message");
            onNewMessageReceived(userName,message);
        }
    };

    public void destroy(){
        socketServiceInterface.setServiceBinded(false);
        AppSignature.getAppContext().unbindService(serviceConnection);
        LocalBroadcastManager.getInstance(AppSignature.getAppContext()).
                unregisterReceiver(socketConnectionReceiver);
        LocalBroadcastManager.getInstance(AppSignature.getAppContext()).
                unregisterReceiver(newMessageReceiver);
    }

    @Override
    public void onSocketConnected() {
        if (activeSocketListener != null) {
            activeSocketListener.onSocketConnected();
        }
    }

    @Override
    public void onSocketDisconnected() {
        if (activeSocketListener != null)
        {
            activeSocketListener.onSocketDisconnected();
        }
    }

    @Override
    public void onNewMessageReceived(String username, String message) {
        if (activeSocketListener != null) {
            activeSocketListener.onNewMessageReceived(username, message);
        }
    }

    public void addOnHandler(String event, Emitter.Listener listener){
        socketServiceInterface.addOnHandler(event, listener);
    }
    public void emit(String event, Object[] args, Ack ack){
        socketServiceInterface.emit(event, args, ack);
    }

    public void emit (String event, Object... args){
        socketServiceInterface.emit(event, args);
    }

     void connect(){
        socketServiceInterface.connect();
    }

    public void disconnect(){
        socketServiceInterface.disconnect();
    }
    public void off(String event) {
        if (socketServiceInterface != null) {
            socketServiceInterface.off(event);
        }
    }

    public boolean isSocketConnected(){
        if (socketServiceInterface == null){
            return false;
        }
        return socketServiceInterface.isSocketConnected();
    }

    public void setAppConnectedToService(Boolean status){
        if ( socketServiceInterface != null){
            socketServiceInterface.setAppConnectedToService(status);
        }
    }

    public void restartSocket(){
        if (socketServiceInterface != null){
         socketServiceInterface.restartSocket();
        }
    }


    public void removeNewMessageHandler(){
        if (socketServiceInterface != null){
            socketServiceInterface.removeMessageHandler();
        }
    }

    public void signOutUser(){
        AppSocketListener.getInstance().disconnect();
        removeNewMessageHandler();
        AppSocketListener.getInstance().connect();
    }
}
