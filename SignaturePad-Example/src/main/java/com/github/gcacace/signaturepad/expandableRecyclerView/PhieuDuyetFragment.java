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
import android.widget.LinearLayout;

import it.gcacace.signaturepad.R;

public class PhieuDuyetFragment  extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_phieu_duyet, container, false);
        Button btnKy = (Button) view.findViewById(R.id.btnKy);
        btnKy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
