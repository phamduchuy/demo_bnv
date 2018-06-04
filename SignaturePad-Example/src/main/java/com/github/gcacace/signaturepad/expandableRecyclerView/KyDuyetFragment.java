package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.github.gcacace.signaturepad.GlobalVar;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import it.gcacace.signaturepad.R;

public class KyDuyetFragment  extends AppCompatActivity implements View.OnClickListener {
    TextView txtTrangthai;
    Button btnOTP;
    SignaturePad signaturePad;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ky_duyet);
        btnOTP = (Button) findViewById(R.id.btnXacthuc);
        signaturePad = (SignaturePad) findViewById(R.id.sgn);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.txtYkien);
        //txtTrangthai = (TextView) findViewById(R.id.txtTrangThai);
        btnOTP.setOnClickListener(this);
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
    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btnXacthuc)
        {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(KyDuyetFragment.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(KyDuyetFragment.this);
            }


            EditText edittext = new EditText(KyDuyetFragment.this);
            edittext.setHint("OTP ");
            builder.setView(edittext);
            builder.setTitle(" Thông báo")
                    .setMessage(" Xác thực OTP qua SMS")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           // txtTrangthai.setText("Phê duyệt thành công");
                           // txtTrangthai.setTextColor(KyDuyetFragment.this.getResources().getColor(R.color.red));
                            GlobalVar.getInstance().setTextYkien(multiAutoCompleteTextView.getText().toString());
                            GlobalVar.getInstance().setBitmap(signaturePad.getSignatureBitmap());
                            finish();
                            btnOTP.setVisibility(View.GONE);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .show();
            addNotification();
        }
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }
}
