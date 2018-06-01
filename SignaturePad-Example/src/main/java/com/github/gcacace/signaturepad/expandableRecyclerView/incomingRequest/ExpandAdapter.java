package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model.ContentModel;
import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model.ContentViewHolder;
import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model.TitleViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import it.gcacace.signaturepad.R;

/**
 * Created by ducqu on 6/1/2018.
 */

public class ExpandAdapter extends ExpandableRecyclerViewAdapter<TitleViewHolder, ContentViewHolder> {
    private Context context;

    public ExpandAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_title_item, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public ContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expand_content_title, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(ContentViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        ContentModel contentModel = (ContentModel) group.getItems().get(childIndex);
        holder.binding(context, contentModel);
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitleExpand(group);
    }
}
