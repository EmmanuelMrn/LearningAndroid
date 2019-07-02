package com.example.launcher;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.example.launcher.adapter.MainMenuAdapter;
import com.example.launcher.models.OptionViewModel;
import com.example.launcher.models.SelectableViewModel;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends JitterbugActivity {

    private static final String[] sMenuOptionsLabels = {
            "Phone",
            "Text",
            "Contacts",
            "Photos & Videos",
            "Tools",
            "Help Guide",
            "Phone Info",
            "Settings",
            "Games",
            "Phone",
            "Text",
            "Contacts",
            "Photos & Videos",
            "Tools",
            "Help Guide",
            "Phone Info",
            "Settings",
            "Games"
    };
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMenuList;
    private RecyclerView.Adapter mMenuAdapter;
    private ScrollerHelper mHelper;
    List<OptionViewModel> mMenuOptionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenuList = findViewById(R.id.recycler_view_menu);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        mMenuList.setLayoutManager(mLinearLayoutManager);

        mMenuOptionsList = new ArrayList<>();

        for(int index = 0; index< sMenuOptionsLabels.length ; index++){
            mMenuOptionsList.add(new OptionViewModel(sMenuOptionsLabels[index]));
        }

        for(OptionViewModel optionViewModel : mMenuOptionsList){
            optionViewModel.setSelectable(true);
        }

        mMenuAdapter = new MainMenuAdapter(this, mMenuOptionsList);
        mMenuList.setAdapter(mMenuAdapter);

        mHelper = new ScrollerHelper((List<SelectableViewModel>)(Object)mMenuOptionsList, mMenuList);

        onStartApp();
    }

    protected void onStartApp(){
        mHelper.highlightFirstItem();
    }

    @Override
    protected void onDownPressed(KeyEvent event) {
        super.onDownPressed(event);

        mHelper.highlightNewItem(true);
        System.out.println("single down");
    }

    @Override
    protected void onUpPressed(KeyEvent event) {
        super.onUpPressed(event);

        mHelper.highlightNewItem(false);
        System.out.println("single up");
    }

    @Override
    protected void onDownLongPressed(KeyEvent event){
        super.onDownLongPressed(event);

        mHelper.highlightItems(true);
        System.out.println("long down");
    }

    @Override
    protected void onUpLongPressed(KeyEvent event){
        super.onUpLongPressed(event);

        mHelper.highlightItems(false);
        System.out.println("long up");
    }

    @Override
    protected void onDownDoublePressed(KeyEvent event){
        super.onDownDoublePressed(event);

        mHelper.highlightDoubleClickItem(true);
        System.out.println("double down");
    }

    @Override
    protected void onUpDoublePressed(KeyEvent event){
        super.onUpDoublePressed(event);

        mHelper.highlightDoubleClickItem(false);
        System.out.println("double up");
    }
}



