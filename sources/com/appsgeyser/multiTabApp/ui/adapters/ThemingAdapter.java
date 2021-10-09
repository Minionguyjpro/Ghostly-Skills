package com.appsgeyser.multiTabApp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.appsgeyser.multiTabApp.utils.ThemePreset;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.wGhostlySkills_14510784.R;

public class ThemingAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private ViewHolder.ViewHolderRadioButtonClicks onClickListener;
    private ThemePreset[] presets;
    private ThemePreset selectedPreset;

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;
        private View view7f0a0095;

        public ViewHolder_ViewBinding(final ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.presetSelectedRadioButton = (RadioButton) Utils.findRequiredViewAsType(view, R.id.color_preset_card_radiobutton, "field 'presetSelectedRadioButton'", RadioButton.class);
            viewHolder.colorAccentImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.color_preset_card_color_accent, "field 'colorAccentImageView'", ImageView.class);
            viewHolder.colorPrimaryImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.color_preset_card_color_primary, "field 'colorPrimaryImageView'", ImageView.class);
            viewHolder.colorPrimaryDarkImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.color_preset_card_color_primary_dark, "field 'colorPrimaryDarkImageView'", ImageView.class);
            View findRequiredView = Utils.findRequiredView(view, R.id.color_preset_card, "method 'themeSelected'");
            this.view7f0a0095 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    viewHolder.themeSelected();
                }
            });
        }
    }

    public ThemingAdapter(ThemePreset[] themePresetArr, Context context2, ViewHolder.ViewHolderRadioButtonClicks viewHolderRadioButtonClicks) {
        this.presets = themePresetArr;
        this.context = context2;
        this.onClickListener = viewHolderRadioButtonClicks;
        this.selectedPreset = ThemePreset.findByNoActionBarName(ThemeUtils.getActivityThemeName(context2));
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.color_preset_card, viewGroup, false), this.onClickListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.presetSelectedRadioButton.setText(this.presets[i].themeTitleId);
        viewHolder.colorAccentImageView.setBackgroundColor(this.context.getResources().getColor(this.presets[i].themeColorAccentId));
        viewHolder.colorPrimaryImageView.setBackgroundColor(this.context.getResources().getColor(this.presets[i].themeColorPrimaryId));
        viewHolder.colorPrimaryDarkImageView.setBackgroundColor(this.context.getResources().getColor(this.presets[i].themeColorPrimaryDarkId));
        checkSelectedPreset(viewHolder, i);
    }

    private void checkSelectedPreset(ViewHolder viewHolder, int i) {
        if (this.presets[i].equals(this.selectedPreset)) {
            viewHolder.presetSelectedRadioButton.setChecked(true);
        } else {
            viewHolder.presetSelectedRadioButton.setChecked(false);
        }
    }

    public int getItemCount() {
        return this.presets.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView
        ImageView colorAccentImageView;
        @BindView
        ImageView colorPrimaryDarkImageView;
        @BindView
        ImageView colorPrimaryImageView;
        ViewHolderRadioButtonClicks onClickListener;
        @BindView
        RadioButton presetSelectedRadioButton;

        public interface ViewHolderRadioButtonClicks {
            void onRadioButtonClicked(int i);
        }

        public ViewHolder(View view, ViewHolderRadioButtonClicks viewHolderRadioButtonClicks) {
            super(view);
            ButterKnife.bind(this, view);
            this.onClickListener = viewHolderRadioButtonClicks;
        }

        /* access modifiers changed from: package-private */
        @OnClick
        public void themeSelected() {
            this.onClickListener.onRadioButtonClicked(getAdapterPosition());
        }
    }
}
