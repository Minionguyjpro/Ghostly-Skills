package com.appsgeyser.multiTabApp.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.appsgeyser.multiTabApp.controllers.BottomMenuController;
import com.appsgeyser.multiTabApp.model.WidgetEntity;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BottomSheetMenuAdapter extends RecyclerView.Adapter<BottomSheetMenuViewHolder> {
    /* access modifiers changed from: private */
    public HashMap<String, Drawable> iconsMap = new HashMap<>();
    private View lastCheckedView;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    /* access modifiers changed from: private */
    public int selectedPosition = -1;
    /* access modifiers changed from: private */
    public List<WidgetEntity> widgetEntities = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(View view, int i, String str);
    }

    public BottomSheetMenuAdapter(List<WidgetEntity> list, OnItemClickListener onItemClickListener2) {
        this.widgetEntities = list;
        this.onItemClickListener = onItemClickListener2;
    }

    public BottomSheetMenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BottomSheetMenuViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bottom_additional_menu_item, viewGroup, false));
    }

    public void onBindViewHolder(final BottomSheetMenuViewHolder bottomSheetMenuViewHolder, int i) {
        boolean z = i == this.selectedPosition;
        bottomSheetMenuViewHolder.bind(this.widgetEntities.get(i), z);
        if (z) {
            this.lastCheckedView = bottomSheetMenuViewHolder.itemView;
        }
        bottomSheetMenuViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = BottomSheetMenuAdapter.this.selectedPosition = bottomSheetMenuViewHolder.getAdapterPosition();
                BottomSheetMenuAdapter.this.onItemClickListener.onItemClick(view, BottomSheetMenuAdapter.this.selectedPosition, ((WidgetEntity) BottomSheetMenuAdapter.this.widgetEntities.get(BottomSheetMenuAdapter.this.selectedPosition)).getTabId());
            }
        });
    }

    public int getItemCount() {
        return this.widgetEntities.size();
    }

    public void setItemChecked(WidgetEntity widgetEntity) {
        for (int i = 0; i < this.widgetEntities.size(); i++) {
            if (this.widgetEntities.get(i).getTabId().equals(widgetEntity.getTabId())) {
                this.selectedPosition = i;
                uncheckLastView();
                notifyItemChanged(i);
                return;
            }
        }
    }

    public void uncheckLastView() {
        View view = this.lastCheckedView;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.bottom_sheet_menu_item_tab_name);
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.materialTransparentWhite500));
        }
    }

    class BottomSheetMenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView tabName;

        BottomSheetMenuViewHolder(View view) {
            super(view);
            this.icon = (ImageView) view.findViewById(R.id.bottom_sheet_menu_item_tab_icon);
            TextView textView = (TextView) view.findViewById(R.id.bottom_sheet_menu_item_tab_name);
            this.tabName = textView;
            textView.setTextColor(view.getContext().getResources().getColorStateList(R.color.bottom_navigation_item_background_colors));
        }

        /* access modifiers changed from: package-private */
        public void bind(WidgetEntity widgetEntity, boolean z) {
            Drawable drawable;
            if (!widgetEntity.getTabIcon().equals("")) {
                if (BottomSheetMenuAdapter.this.iconsMap.containsKey(widgetEntity.getTabId())) {
                    drawable = (Drawable) BottomSheetMenuAdapter.this.iconsMap.get(widgetEntity.getTabId());
                } else {
                    Context context = this.icon.getContext();
                    drawable = BottomMenuController.getDrawableIconFromAssets(context, "tabIcons/" + widgetEntity.getTabIcon());
                    BottomSheetMenuAdapter.this.iconsMap.put(widgetEntity.getTabId(), drawable);
                }
                if (drawable != null) {
                    this.icon.setImageDrawable(drawable);
                    this.icon.setVisibility(0);
                } else {
                    this.icon.setVisibility(8);
                }
            } else {
                this.icon.setVisibility(8);
            }
            this.tabName.setText(widgetEntity.getTabName());
            if (z) {
                this.tabName.setTextColor(this.icon.getContext().getResources().getColor(R.color.white));
            } else {
                this.tabName.setTextColor(this.icon.getContext().getResources().getColor(R.color.materialTransparentWhite500));
            }
        }
    }
}
