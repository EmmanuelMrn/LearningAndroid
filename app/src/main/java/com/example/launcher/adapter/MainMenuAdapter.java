package com.example.launcher.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.launcher.R;
import com.example.launcher.models.OptionViewModel;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ListViewHolder> {

    List<OptionViewModel> mOptionsList;
    private Context mContext;

    public MainMenuAdapter(Context context, List<OptionViewModel> list){
        this.mContext = context;
        this.mOptionsList = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, final int position) {
        listViewHolder.getmOptionItem().setText(mOptionsList.get(position).getOption());
        listViewHolder.getmNotificationOvalItem().setVisibility(View.VISIBLE);

        listViewHolder.setupComponentWithData(mOptionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mOptionsList != null ? mOptionsList.size() : 0;
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout container;
        private TextView mOptionItem;
        private TextView mNotificationOvalItem;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.linearlayout_view_container);
            mOptionItem = itemView.findViewById(R.id.label_adapter_item);
            mNotificationOvalItem = itemView.findViewById(R.id.oval_adapter_item);
        }

        public void setupComponentWithData(OptionViewModel optionViewModel) {
            if (optionViewModel.isHighLighted()) {
                container.setBackgroundResource(R.drawable.rounded_corner_label_selected);
                mNotificationOvalItem.setBackgroundResource(R.drawable.rounder_corner_oval_with_stroke);
                mOptionItem.setTextColor(Color.parseColor("#FFFFFF"));

            } else {
                container.setBackgroundResource(R.drawable.rounded_corner_label);
                mNotificationOvalItem.setBackgroundResource(R.drawable.rounded_corner_oval);
                mOptionItem.setTextColor(Color.parseColor("#000000"));
            }
        }

        public TextView getmOptionItem() {
            return mOptionItem;
        }

        public TextView getmNotificationOvalItem() { return mNotificationOvalItem; }
    }
}
