package com.example.launcher.models;

public class SelectableViewModel {

    boolean mIsSelectable;
    boolean mIsHighLighted;


    public boolean isSelectable() {
        return mIsSelectable;
    }

    public void setSelectable(boolean isSelectable) {
        this.mIsSelectable = isSelectable;
    }

    public boolean isHighLighted() {
        return mIsHighLighted;
    }

    public void setIsHighLighted(boolean isHighlighted) {
        this.mIsHighLighted = isHighlighted;
    }
}
