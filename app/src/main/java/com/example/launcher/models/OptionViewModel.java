package com.example.launcher.models;

public class OptionViewModel extends SelectableViewModel{
    private String mOptionLabel;

    public OptionViewModel(String optionLabel) {
        mOptionLabel = optionLabel;
    }

    public String getOption() {
        return mOptionLabel;
    }

}
