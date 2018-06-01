package com.github.gcacace.signaturepad.expandableRecyclerView.incomingRequest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ducqu on 6/1/2018.
 */

public class ContentModel implements Parcelable {
    private String name;
    private String content = "", time = "", status = "", manager = "", avatar = "";

    public ContentModel(String name, String content, String time, String status, String manager, String avatar) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.status = status;
        this.manager = manager;
        this.avatar = avatar;
    }

    protected ContentModel(Parcel in) {
        name = in.readString();
        content = in.readString();
        time = in.readString();
        status = in.readString();
        manager = in.readString();
        avatar = in.readString();
    }

    public static final Creator<ContentModel> CREATOR = new Creator<ContentModel>() {
        @Override
        public ContentModel createFromParcel(Parcel in) {
            return new ContentModel(in);
        }

        @Override
        public ContentModel[] newArray(int size) {
            return new ContentModel[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
