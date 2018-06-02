package com.github.gcacace.signaturepad;

public class GlobalVar {
    public int getMyVar() {
        return myVar;
    }

    public void setMyVar(int myVar) {
        this.myVar = myVar;
    }

    private int myVar = 0;
    private boolean isShowPheDuyet = false;

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
