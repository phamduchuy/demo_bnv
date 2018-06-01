package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.editDoc.EditDocumentActivity;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import it.gcacace.signaturepad.R;

/**
 * Created by ducqu on 6/1/2018.
 */

public class ContentViewHolder extends ChildViewHolder
{
    private TextView contentExpand, name, time, status, manager;
    private LinearLayout llStatus;

    public ContentViewHolder(View itemView)
    {
        super(itemView);

        contentExpand = (TextView) itemView.findViewById(R.id.contentExpand);
        name = (TextView) itemView.findViewById(R.id.name);
        time = (TextView) itemView.findViewById(R.id.time);
        status = (TextView) itemView.findViewById(R.id.status);
        manager = (TextView) itemView.findViewById(R.id.manager);
        llStatus = (LinearLayout) itemView.findViewById(R.id.llStatus);

    }

    public void binding(final Context context, final ContentModel contentModel)
    {
        name.setText(contentModel.getName());
        contentExpand.setText(contentModel.getContent());
        time.setText(contentModel.getTime());
        if (contentModel.getStatus().equals("0"))
        {
            status.setText("Đã hoàn thành");
            llStatus.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.boder_coner));
        }
        else if (contentModel.getStatus().equals("1"))
        {
            status.setText("Chưa trả lời");
            llStatus.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.boder_coner_cho));
        }
        else
        {
            status.setText("Đã quá hạn");
            llStatus.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.boder_coner_huy));
        }

        manager.setText(contentModel.getManager());

        llStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentModel.getStatus().equals("1"))
                {
                    context.startActivity(new Intent(context, EditDocumentActivity.class));
                }
            }
        });
    }
}
