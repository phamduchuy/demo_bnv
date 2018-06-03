package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import it.gcacace.signaturepad.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edt,edtPhone;
    boolean OTP = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edt = (EditText) findViewById(R.id.editText2);
        edtPhone = (EditText) findViewById(R.id.editText);

    }

    public void ClickLogin(View view)
    {
        if (view.getId() == R.id.btnLogin) {
            if (OTP)
            {
                finish();
                startActivity(new Intent(this, RequestActivity.class));
            }
            else
                {
                    if (edtPhone.getText().toString().length()>0 && !OTP) {
                        addNotification();
                        edt.setVisibility(View.VISIBLE);
                        OTP = true;
                        btnLogin.setText("Gửi");
                    }

                }
        }
    }
    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("OTP")
                        .setContentText("OTP : 123456");

        Intent notificationIntent = new Intent(this, LoginActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        builder.setSmallIcon(R.drawable.ic_launcher);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
