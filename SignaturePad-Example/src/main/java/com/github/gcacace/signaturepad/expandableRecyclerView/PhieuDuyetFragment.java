package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.gcacace.signaturepad.GlobalVar;
import com.github.gcacace.signaturepad.views.SignaturePad;

import it.gcacace.signaturepad.R;

public class PhieuDuyetFragment  extends Fragment implements View.OnClickListener{

    ImageView imgChuKy;
    Button btnKy;
    TextView txtYkien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_phieu_duyet, container, false);
         btnKy = (Button) view.findViewById(R.id.btnKy);
        imgChuKy = (ImageView) view.findViewById(R.id.imgChuky);
        txtYkien = (TextView) view.findViewById(R.id.txtYkien);

        btnKy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (GlobalVar.getInstance().getBitmap() != null)
        {
            imgChuKy.setImageBitmap(GlobalVar.getInstance().getBitmap());
            txtYkien.setText(GlobalVar.getInstance().getTextYkien());
            btnKy.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btnKy)
        {
            startActivity(new Intent(getActivity(),KyDuyetFragment.class));
        }
    }
}
