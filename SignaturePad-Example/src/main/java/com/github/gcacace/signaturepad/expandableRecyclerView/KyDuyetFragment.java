package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.gcacace.signaturepad.R;

public class KyDuyetFragment  extends Fragment implements View.OnClickListener {
    TextView txtTrangthai;
    Button btnOTP;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_ky_duyet, container, false);
         btnOTP = (Button) view.findViewById(R.id.btnXacthuc);
        txtTrangthai = (TextView) view.findViewById(R.id.txtTrangThai);
        btnOTP.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btnXacthuc)
        {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getActivity());
            }
            EditText edittext = new EditText(getActivity());
            edittext.setHint("OTP ");
            builder.setView(edittext);
            builder.setTitle(" Thông báo")
                    .setMessage(" Xác thực OTP qua SMS")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            txtTrangthai.setText("Phê duyệt thành công");
                            txtTrangthai.setTextColor(getActivity().getResources().getColor(R.color.red));
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
