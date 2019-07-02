package com.example.launcher;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.launcher.models.SelectableViewModel;

import java.util.List;

public class ScrollerHelper {

    private int selectedItem = 1;
    private List<SelectableViewModel> mViewHolders;
    private RecyclerView recyclerView;

    public ScrollerHelper(List<SelectableViewModel> optionList, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.mViewHolders = optionList;
    }

    public void highlightFirstItem(){
        /// This will select the first element of the list
        selectedItem = 0;
        // Change color all element to white
        clearHighlight();
        // Change selected item to highlight
        mViewHolders.get(selectedItem).setIsHighLighted(true);
        recyclerView.getAdapter().notifyDataSetChanged();
        scrollToPosition(selectedItem);
    }

    public void highlightDoubleClickItem(boolean doubleClickDown){
        if (doubleClickDown) {
            selectedItem = mViewHolders.size() - 1;
            scrollSelectedToPosition(selectedItem);
        }
        else {
            selectedItem = 0;
            scrollSelectedToPosition(selectedItem);
        }
    }

    public void scrollSelectedToPosition(int selectedItemIndex){
        clearHighlight();
        mViewHolders.get(selectedItemIndex).setIsHighLighted(true);
        recyclerView.getAdapter().notifyDataSetChanged();
        scrollToPosition(selectedItemIndex);
        recyclerView.scrollToPosition(selectedItemIndex);
    }

    public void highlightItems(boolean goingDown){
        selectedItem = getHighlightIndex(goingDown);
        // Change color all element to white
        clearHighlight();
        // Change selected item to highlight
        mViewHolders.get(selectedItem).setIsHighLighted(true);
        recyclerView.getAdapter().notifyDataSetChanged();
        scrollToPosition(selectedItem);
    }

    public void highlightNewItem(boolean goingDown) {
        /// This will search the next contact row to get the index of selected item
        selectedItem = getHighlightIndex(goingDown);
        // Change color all element to white
        clearHighlight();
        // Change selected item to highlight
        mViewHolders.get(selectedItem).setIsHighLighted(true);
        recyclerView.getAdapter().notifyDataSetChanged();
        scrollToPosition(selectedItem);
    }

    private int getHighlightIndex(boolean isGoingDown) {
        int selectedItem = this.selectedItem;
        if (isGoingDown) {
            for (int index = selectedItem + 1; index < mViewHolders.size(); index++) {
                if (mViewHolders.get(index).isSelectable()) {
                    selectedItem = index;
                    break;
                }
            }
        } else {
            for (int index = selectedItem - 1; index >= 0; index--) {
                if (mViewHolders.get(index).isSelectable()) {
                    selectedItem = index;
                    break;
                }
            }
        }
        return selectedItem;
    }

    private void clearHighlight() {
        for (SelectableViewModel contact : mViewHolders) {
            contact.setIsHighLighted(false);
        }
    }

    private void scrollToPosition(int viewIndex) {
        if (recyclerView == null) {
            return;
        }
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View view = manager.findViewByPosition(viewIndex);
        if (view == null) {
            return;
        }
        boolean isVertical = manager.getOrientation() == LinearLayoutManager.VERTICAL;
        int tWidthItem = Math.round(isVertical ? view.getHeight() : view.getWidth());
        int[] originalPositionsHighlight = new int[2];
        int[] originalPositionsRecycler = new int[2];
        recyclerView.getLocationInWindow(originalPositionsRecycler);
        view.getLocationInWindow(originalPositionsHighlight);
        int distanceToEdgeOfScreen = originalPositionsRecycler[isVertical ? 1 : 0];
        int distanceToEdge = originalPositionsHighlight[isVertical ? 1 : 0] - distanceToEdgeOfScreen;
        int middleLength = (isVertical ? recyclerView.getHeight() : recyclerView.getHeight()) / 2;
        int lengthToBeScrolled = middleLength - distanceToEdge - (tWidthItem / 2);
        if (manager.findFirstCompletelyVisibleItemPosition() == 0 && distanceToEdge < middleLength) {
            return;
        }
        recyclerView.smoothScrollBy(0, lengthToBeScrolled * -1);
    }


}
