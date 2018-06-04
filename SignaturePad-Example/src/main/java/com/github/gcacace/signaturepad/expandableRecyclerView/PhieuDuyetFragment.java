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

    ImageView imgChuKy5,imgChuKy6;
    Button btnKy5,btnKy6;
    TextView txtYkien5,txtYkien6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_phieu_duyet, container, false);
         btnKy5 = (Button) view.findViewById(R.id.btnKy5);
        imgChuKy5 = (ImageView) view.findViewById(R.id.imgChuky5);
        txtYkien5 = (TextView) view.findViewById(R.id.txtYkien5);

        btnKy5.setOnClickListener(this);

        btnKy6 = (Button) view.findViewById(R.id.btnKy6);
        imgChuKy6 = (ImageView) view.findViewById(R.id.imgChuky6);
        txtYkien6 = (TextView) view.findViewById(R.id.txtYkien6);

        btnKy6.setOnClickListener(this);
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
            imgChuKy5.setImageBitmap(GlobalVar.getInstance().getBitmap());
            txtYkien5.setText(GlobalVar.getInstance().getTextYkien());
            btnKy5.setVisibility(View.GONE);
        }

        if (GlobalVar.getInstance().isShowView6())
        {
            btnKy6.setVisibility(View.VISIBLE);
        }

        if(GlobalVar.getInstance().getBuoc()=="Buoc5")
        {
            imgChuKy6.setImageBitmap(GlobalVar.getInstance().getBitmap5());
            imgChuKy6.setVisibility(View.VISIBLE);
            txtYkien6.setText(GlobalVar.getInstance().getTextYkien());
            txtYkien6.setVisibility(View.VISIBLE);
            btnKy6.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btnKy5)
        {
            GlobalVar.getInstance().setBuoc("Buoc4");
            startActivity(new Intent(getActivity(),KyDuyetFragment.class));
        }
        if (view.getId() == R.id.btnKy6)
        {
            GlobalVar.getInstance().setBuoc("Buoc5");
            startActivity(new Intent(getActivity(),KyDuyetFragment.class));

        }
    }
}
