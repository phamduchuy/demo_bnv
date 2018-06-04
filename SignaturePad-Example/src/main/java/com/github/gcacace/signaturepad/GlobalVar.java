package com.github.gcacace.signaturepad;

import android.graphics.Bitmap;

public class GlobalVar {
    public int getMyVar() {
        return myVar;
    }

    public void setMyVar(int myVar) {
        this.myVar = myVar;
    }

    private boolean showView6 = false;
    private String buoc = "Buoc4";

    public String getBuoc() {
        return buoc;
    }

    public void setBuoc(String buoc) {
        this.buoc = buoc;
    }

    public boolean isShowView6() {
        return showView6;
    }

    public void setShowView6(boolean showView6) {
        this.showView6 = showView6;
    }

    private String textYkien ="";

    public String getTextYkien() {
        return textYkien;
    }

    public void setTextYkien(String textYkien) {
        this.textYkien = textYkien;
    }

    private int myVar = 0;
    private boolean isShowPheDuyet = false;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isShowPheDuyet() {
        return isShowPheDuyet;
    }

    public void setShowPheDuyet(boolean showPheDuyet) {
        isShowPheDuyet = showPheDuyet;
    }

    private static GlobalVar instance;

    static {
        instance = new GlobalVar();
    }

    private GlobalVar() {
    }

    public static GlobalVar getInstance() {
        return GlobalVar.instance;
    }

}
