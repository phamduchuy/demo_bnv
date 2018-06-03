package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.gcacace.signaturepad.R;

public class KyDuyetFragment  extends AppCompatActivity implements View.OnClickListener {
    TextView txtTrangthai;
    Button btnOTP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ky_duyet);
        btnOTP = (Button) findViewById(R.id.btnXacthuc);
        txtTrangthai = (TextView) findViewById(R.id.txtTrangThai);
        btnOTP.setOnClickListener(this);
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
                            txtTrangthai.setText("Phê duyệt thành công");
                            txtTrangthai.setTextColor(KyDuyetFragment.this.getResources().getColor(R.color.red));
                            btnOTP.setVisibility(View.GONE);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .show();
        }
    }
}
