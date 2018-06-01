package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import it.gcacace.signaturepad.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by ducqu on 6/1/2018.
 */

public class TitleViewHolder extends GroupViewHolder {
    TextView titleExpand;
    private ImageView arrow;

    public TitleViewHolder(View itemView) {
        super(itemView);

        titleExpand = (TextView) itemView.findViewById(R.id.titleExpand);
        arrow = (ImageView) itemView.findViewById(R.id.arrow);
    }

    public void setTitleExpand(ExpandableGroup group)
    {
        if (group instanceof TitleModel)
        {
            titleExpand.setText(group.getTitle());
        }
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    @Override
    public void expand() {
        animateExpand();
    }

    private void animateExpand() {
        RotateAnimation rotate = new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate = new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
