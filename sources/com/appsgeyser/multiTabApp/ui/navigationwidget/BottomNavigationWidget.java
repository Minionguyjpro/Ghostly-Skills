package com.appsgeyser.multiTabApp.ui.navigationwidget;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import com.appsgeyser.multiTabApp.configuration.WebWidgetConfiguration;
import com.appsgeyser.multiTabApp.suggestions.RemoteSuggestionItem;
import com.appsgeyser.multiTabApp.suggestions.SuggestionsClient;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.Collection;

public class BottomNavigationWidget extends NavigationWidget {
    public void hideSuggestionsView() {
    }

    public void onReceiveSuggestions(ArrayList<RemoteSuggestionItem> arrayList, String str) {
    }

    public void requestSuggestions() {
    }

    public BottomNavigationWidget(ViewGroup viewGroup, String str, View view, Collection<UrlBarMenuButton> collection) {
        super(viewGroup, str, view, collection);
        this._parent = viewGroup;
        this._progressBarContainer = (RelativeLayout) viewGroup.findViewById(R.id.progressbarPanel);
        this._defaultUrl = str;
        this._browser = view;
        createWidgetLayout();
        this.suggestionsClient = new SuggestionsClient(Factory.getInstance().getMainNavigationActivity());
        this.suggestionsClient.setListener(this);
        this.refreshImage = R.drawable.ic_refresh_white_24dp;
        this.stopImage = R.drawable.ic_close_white_24dp;
        this.urlBarButton = R.layout.url_bar_top_menu_button;
        this.urlBarCheckBox = R.layout.url_bar_top_menu_checkbox;
        this.urlBarIcon = R.layout.url_bar_top_icon;
        this.suggestionsView = (ViewGroup) this._parent.findViewById(R.id.suggestions);
        this.suggestionsViewScroll = (ViewGroup) this._parent.findViewById(R.id.suggestionsScroll);
        this.overlay = (ViewGroup) this._parent.findViewById(R.id.menuOverlay);
        this.overlayScroll = (ViewGroup) this._parent.findViewById(R.id.menuOverlayScroll);
        this.urlTextBox = (AutoCompleteTextView) this._parent.findViewById(R.id.urlTextbox);
        this.searchBackground = (LinearLayout) this._parent.findViewById(R.id.search_background);
        this.stopRefreshButton = (ImageButton) this._parent.findViewById(R.id.stopRefreshButton);
        this.searchBtn = (ImageView) this.searchBackground.findViewById(R.id.search_icon);
        this.clearTextButton = (ImageButton) this._parent.findViewById(R.id.clearText);
        this.clearTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BottomNavigationWidget.this.urlTextBox.setText("");
            }
        });
        this.urlTextBox.addTextChangedListener(this.urlBarTextChangeListener);
        this.urlTextBox.setOnFocusChangeListener(this.focusChangeListener);
        this.urlTextBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BottomNavigationWidget.this.focusChangeListener.onFocusChange(view, true);
            }
        });
        initEventHandlers();
    }

    public void createWidgetLayout() {
        Context context = this._parent.getContext();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.navigation_bar, this._parent, true);
        this.urlBar = (Toolbar) this._parent.findViewById(R.id.bottomNavigationRow);
        this.urlBar.bringToFront();
        if (!Factory.getInstance().getMainNavigationActivity().getConfig().getApplicationTheme().equals(WebWidgetConfiguration.ApplicationThemes.ACTION_BAR)) {
            this.urlBar.inflateMenu(R.menu.webapp_menu);
            final MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
            mainNavigationActivity.runOnUiThread(new Runnable() {
                public void run() {
                    mainNavigationActivity.setSupportActionBar(BottomNavigationWidget.this.urlBar);
                }
            });
            AppsgeyserSDK.isAboutDialogEnabled(context, new AppsgeyserSDK.OnAboutDialogEnableListener() {
                public void onDialogEnableReceived(boolean z) {
                    if (!z) {
                        BottomNavigationWidget.this.urlBar.getMenu().findItem(R.id.webapp_about).setVisible(false);
                    }
                }
            });
        }
        this._defaultTopBrowserMargin = 0;
        this._defaultBottomBrowserMargin = (int) TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics());
        show();
    }

    public void hide() {
        if (this._parent.findViewById(R.id.bottomNavigationRow) != null) {
            this._parent.findViewById(R.id.bottomNavigationRow).setVisibility(8);
            ((RelativeLayout.LayoutParams) this._browser.getLayoutParams()).setMargins(0, 0, 0, 0);
            ((RelativeLayout.LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(0, 0, 0, 0);
            this._visible = false;
        }
    }

    public void show() {
        if (this._parent.findViewById(R.id.bottomNavigationRow) != null) {
            this._parent.findViewById(R.id.bottomNavigationRow).setVisibility(0);
            ((RelativeLayout.LayoutParams) this._browser.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            ((RelativeLayout.LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            this._visible = true;
        }
    }

    public void changeVisibilityAnimated(final boolean z) {
        if (this._parent.findViewById(R.id.bottomNavigationRow) == null) {
            return;
        }
        if (this._visible && z) {
            return;
        }
        if (this._visible || z) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this._parent.getContext(), z ? R.anim.slide_bottom_down : R.anim.slide_bottom_up);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (z) {
                        BottomNavigationWidget.this.show();
                    } else {
                        BottomNavigationWidget.this.hide();
                    }
                }
            });
            loadAnimation.setDuration(300);
            this._parent.findViewById(R.id.bottomNavigationRow).startAnimation(loadAnimation);
            AnonymousClass6 r0 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    float f2;
                    if (z) {
                        f2 = ((float) BottomNavigationWidget.this._defaultTopBrowserMargin) * f;
                    } else {
                        f2 = ((float) BottomNavigationWidget.this._defaultTopBrowserMargin) - (((float) BottomNavigationWidget.this._defaultTopBrowserMargin) * f);
                    }
                    int i = (int) f2;
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) BottomNavigationWidget.this._browser.getLayoutParams();
                    layoutParams.setMargins(0, i, 0, 0);
                    BottomNavigationWidget.this._browser.setLayoutParams(layoutParams);
                }
            };
            r0.setDuration(300);
            this._parent.startAnimation(r0);
        }
    }

    public void hideAnimated() {
        changeVisibilityAnimated(false);
    }

    public void showAnimated() {
        changeVisibilityAnimated(true);
    }
}
