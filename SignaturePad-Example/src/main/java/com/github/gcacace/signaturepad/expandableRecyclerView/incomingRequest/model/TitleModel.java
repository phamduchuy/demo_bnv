package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model;

import android.os.Parcel;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by ducqu on 6/1/2018.
 */

public class TitleModel extends ExpandableGroup<ContentModel> {
    public TitleModel(String title, List<ContentModel> items) {
        super(title, items);
    }

    public TitleModel(Parcel in, int iconResId) {
        super(in);
    }

    protected TitleModel(Parcel in) {
        super(in);
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (!(obj instanceof TitleModel)) return false;
//
//        TitleModel genre = (TitleModel) obj;
//        return getTitle() == genre.getTitle();
//    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof TitleModel)) return false;
        return super.equals(obj);
    }
}
