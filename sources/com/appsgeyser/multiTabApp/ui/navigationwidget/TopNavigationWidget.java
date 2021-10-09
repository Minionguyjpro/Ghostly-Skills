package com.appsgeyser.multiTabApp.ui.navigationwidget;

import android.content.Context;
import android.database.Cursor;
import android.util.Patterns;
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
import com.appsgeyser.multiTabApp.suggestions.LocalSuggestionItem;
import com.appsgeyser.multiTabApp.suggestions.RemoteSuggestionItem;
import com.appsgeyser.multiTabApp.suggestions.SuggestionItem;
import com.appsgeyser.multiTabApp.suggestions.SuggestionsClient;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.google.android.gms.plus.PlusShare;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.Collection;

public class TopNavigationWidget extends NavigationWidget {
    protected View.OnClickListener _refreshOnclickListener = new View.OnClickListener() {
        public void onClick(View view) {
            TopNavigationWidget.this.hideAdditionalMenu();
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().reload();
        }
    };

    public void requestSuggestions() {
        final String obj = this.urlTextBox.getText().toString();
        this.handler.removeCallbacksAndMessages((Object) null);
        if (obj == null || obj.length() <= 0 || obj.length() >= 30 || Patterns.WEB_URL.matcher(obj).matches()) {
            hideSuggestionsView();
        } else {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    TopNavigationWidget.this.suggestionsClient.getSuggestionsAsync(obj);
                }
            }, 300);
        }
    }

    public void hideSuggestionsView() {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (TopNavigationWidget.this.suggestionsView != null) {
                    TopNavigationWidget.this.suggestionsView.setVisibility(8);
                    TopNavigationWidget.this.suggestionsView.removeAllViews();
                }
                if (TopNavigationWidget.this.suggestionsViewScroll != null) {
                    TopNavigationWidget.this.suggestionsViewScroll.setVisibility(8);
                }
                TopNavigationWidget.this._suggestionsVisible = false;
            }
        });
    }

    public TopNavigationWidget(ViewGroup viewGroup, String str, View view, Collection<UrlBarMenuButton> collection) {
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
                TopNavigationWidget.this.urlTextBox.setText("");
            }
        });
        this.urlTextBox.addTextChangedListener(this.urlBarTextChangeListener);
        this.urlTextBox.setOnFocusChangeListener(this.focusChangeListener);
        this.urlTextBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TopNavigationWidget.this.focusChangeListener.onFocusChange(view, true);
            }
        });
        initEventHandlers();
    }

    public void createWidgetLayout() {
        Context context = this._parent.getContext();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.navigation_bar_top, this._parent, true);
        this.urlBar = (Toolbar) this._parent.findViewById(R.id.topNavigationRow);
        this.urlBar.bringToFront();
        this.urlBar.inflateMenu(R.menu.webapp_menu);
        AppsgeyserSDK.isAboutDialogEnabled(context, new AppsgeyserSDK.OnAboutDialogEnableListener() {
            public void onDialogEnableReceived(boolean z) {
                if (!z) {
                    TopNavigationWidget.this.urlBar.getMenu().findItem(R.id.webapp_about).setVisible(false);
                }
            }
        });
        final MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
        mainNavigationActivity.runOnUiThread(new Runnable() {
            public void run() {
                mainNavigationActivity.setSupportActionBar(TopNavigationWidget.this.urlBar);
            }
        });
        this._defaultTopBrowserMargin = (int) TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics());
        this._defaultBottomBrowserMargin = 0;
        show();
    }

    public void hide() {
        if (this._parent.findViewById(R.id.topNavigationRow) != null) {
            this._parent.findViewById(R.id.topNavigationRow).setVisibility(8);
            ((RelativeLayout.LayoutParams) this._browser.getLayoutParams()).setMargins(0, 0, 0, 0);
            ((RelativeLayout.LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(0, 0, 0, 0);
            this._visible = false;
        }
    }

    public void show() {
        if (this._parent.findViewById(R.id.topNavigationRow) != null) {
            this._parent.findViewById(R.id.topNavigationRow).setVisibility(0);
            ((RelativeLayout.LayoutParams) this._browser.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            ((RelativeLayout.LayoutParams) this._progressBarContainer.getLayoutParams()).setMargins(this._defaultLeftBrowserMargin, this._defaultTopBrowserMargin, this._defaultRightBrowserMargin, this._defaultBottomBrowserMargin);
            this._visible = true;
        }
    }

    public void changeVisibilityAnimated(final boolean z) {
        if (this._parent.findViewById(R.id.topNavigationRow) == null) {
            return;
        }
        if (this._visible && z) {
            return;
        }
        if (this._visible || z) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this._parent.getContext(), z ? R.anim.slide_down : R.anim.slide_up);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    if (z) {
                        TopNavigationWidget.this.show();
                    } else {
                        TopNavigationWidget.this.hide();
                    }
                }
            });
            loadAnimation.setDuration(300);
            this._parent.findViewById(R.id.topNavigationRow).startAnimation(loadAnimation);
            AnonymousClass8 r0 = new Animation() {
                /* access modifiers changed from: protected */
                public void applyTransformation(float f, Transformation transformation) {
                    float f2;
                    if (z) {
                        f2 = ((float) TopNavigationWidget.this._defaultTopBrowserMargin) * f;
                    } else {
                        f2 = ((float) TopNavigationWidget.this._defaultTopBrowserMargin) - (((float) TopNavigationWidget.this._defaultTopBrowserMargin) * f);
                    }
                    int i = (int) f2;
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) TopNavigationWidget.this._browser.getLayoutParams();
                    layoutParams.setMargins(TopNavigationWidget.this._defaultLeftBrowserMargin, i, TopNavigationWidget.this._defaultRightBrowserMargin, TopNavigationWidget.this._defaultBottomBrowserMargin);
                    TopNavigationWidget.this._browser.setLayoutParams(layoutParams);
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

    public void hideAdditionalMenu() {
        if (this._menuVisible) {
            this._menuVisible = false;
            ViewGroup.LayoutParams layoutParams = this.overlay.getLayoutParams();
            layoutParams.height = -2;
            this.overlay.setLayoutParams(layoutParams);
            this.overlay.startAnimation(this.slideUp);
        }
    }

    public boolean isSuggestionsVisible() {
        return this._suggestionsVisible;
    }

    private ArrayList<LocalSuggestionItem> getLocalSuggestions(String str) {
        Cursor historyItemsGroupedByUrl = this._history.getHistoryItemsGroupedByUrl(str);
        ArrayList<LocalSuggestionItem> arrayList = new ArrayList<>();
        try {
            if (historyItemsGroupedByUrl.moveToFirst()) {
                do {
                    String string = historyItemsGroupedByUrl.getString(historyItemsGroupedByUrl.getColumnIndex(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                    String string2 = historyItemsGroupedByUrl.getString(historyItemsGroupedByUrl.getColumnIndex("url"));
                    if (!isHomePageUrl(string2)) {
                        arrayList.add(new LocalSuggestionItem(string, string2));
                    }
                } while (historyItemsGroupedByUrl.moveToNext());
            }
            historyItemsGroupedByUrl.close();
            return arrayList;
        } catch (Exception unused) {
            return new ArrayList<>();
        }
    }

    /* access modifiers changed from: private */
    public SuggestionItem _getNextSuggestion(ArrayList<LocalSuggestionItem> arrayList, int i, ArrayList<RemoteSuggestionItem> arrayList2, int i2, int i3) {
        if (i3 <= 2) {
            if (i2 < arrayList2.size()) {
                return arrayList2.get(i2);
            }
            if (i < arrayList.size()) {
                return arrayList.get(i);
            }
            return null;
        } else if (i < arrayList.size()) {
            return arrayList.get(i);
        } else {
            if (i2 < arrayList2.size()) {
                return arrayList2.get(i2);
            }
            return null;
        }
    }

    public synchronized void onReceiveSuggestions(final ArrayList<RemoteSuggestionItem> arrayList, String str) {
        final ArrayList<LocalSuggestionItem> localSuggestions = getLocalSuggestions(str);
        final Context context = this._parent.getContext();
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
                r1 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.access$000(r13.this$0, r5, r8, r4, r9, r7);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r13 = this;
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r0 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    android.view.ViewGroup r0 = r0.suggestionsView
                    r0.removeAllViews()
                    r0 = 0
                    r7 = 0
                    r8 = 0
                    r9 = 0
                L_0x000b:
                    r1 = 6
                    if (r7 >= r1) goto L_0x00ba
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r1 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    java.util.ArrayList r2 = r5
                    java.util.ArrayList r4 = r4
                    r3 = r8
                    r5 = r9
                    r6 = r7
                    com.appsgeyser.multiTabApp.suggestions.SuggestionItem r1 = r1._getNextSuggestion(r2, r3, r4, r5, r6)
                    if (r1 != 0) goto L_0x001f
                    goto L_0x00ba
                L_0x001f:
                    android.content.Context r2 = r0
                    java.lang.String r3 = "layout_inflater"
                    java.lang.Object r2 = r2.getSystemService(r3)
                    android.view.LayoutInflater r2 = (android.view.LayoutInflater) r2
                    r3 = 2131558565(0x7f0d00a5, float:1.874245E38)
                    r4 = 0
                    android.view.View r2 = r2.inflate(r3, r4)
                    android.view.ViewGroup r2 = (android.view.ViewGroup) r2
                    r3 = 2131362281(0x7f0a01e9, float:1.8344338E38)
                    android.view.View r3 = r2.findViewById(r3)
                    android.widget.TextView r3 = (android.widget.TextView) r3
                    r4 = 2131362280(0x7f0a01e8, float:1.8344336E38)
                    android.view.View r4 = r2.findViewById(r4)
                    android.widget.ImageView r4 = (android.widget.ImageView) r4
                    boolean r5 = r1 instanceof com.appsgeyser.multiTabApp.suggestions.LocalSuggestionItem
                    if (r5 == 0) goto L_0x008f
                    int r8 = r8 + 1
                    r5 = r1
                    com.appsgeyser.multiTabApp.suggestions.LocalSuggestionItem r5 = (com.appsgeyser.multiTabApp.suggestions.LocalSuggestionItem) r5
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r10 = r5.getTitle()
                    r6.append(r10)
                    java.lang.String r10 = "\n"
                    r6.append(r10)
                    java.lang.String r10 = r5.getUrl()
                    r6.append(r10)
                    java.lang.String r6 = r6.toString()
                    android.text.SpannableStringBuilder r10 = new android.text.SpannableStringBuilder
                    r10.<init>(r6)
                    android.text.style.ForegroundColorSpan r11 = new android.text.style.ForegroundColorSpan
                    r12 = 255(0xff, float:3.57E-43)
                    int r12 = android.graphics.Color.rgb(r0, r0, r12)
                    r11.<init>(r12)
                    java.lang.String r5 = r5.getTitle()
                    int r5 = r5.length()
                    int r6 = r6.length()
                    r12 = 18
                    r10.setSpan(r11, r5, r6, r12)
                    r3.setText(r10)
                    goto L_0x009f
                L_0x008f:
                    boolean r5 = r1 instanceof com.appsgeyser.multiTabApp.suggestions.RemoteSuggestionItem
                    if (r5 == 0) goto L_0x00b6
                    int r9 = r9 + 1
                    r5 = r1
                    com.appsgeyser.multiTabApp.suggestions.RemoteSuggestionItem r5 = (com.appsgeyser.multiTabApp.suggestions.RemoteSuggestionItem) r5
                    java.lang.String r5 = r5.getAutocompleteText()
                    r3.setText(r5)
                L_0x009f:
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget$11$1 r5 = new com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget$11$1
                    r5.<init>(r1)
                    r3.setOnClickListener(r5)
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget$11$2 r3 = new com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget$11$2
                    r3.<init>(r1)
                    r4.setOnClickListener(r3)
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r1 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    android.view.ViewGroup r1 = r1.suggestionsView
                    r1.addView(r2)
                L_0x00b6:
                    int r7 = r7 + 1
                    goto L_0x000b
                L_0x00ba:
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r1 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    android.view.ViewGroup r1 = r1.suggestionsViewScroll
                    r1.setVisibility(r0)
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r1 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    android.view.ViewGroup r1 = r1.suggestionsView
                    r1.setVisibility(r0)
                    com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget r0 = com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.this
                    r1 = 1
                    r0._suggestionsVisible = r1
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.multiTabApp.ui.navigationwidget.TopNavigationWidget.AnonymousClass11.run():void");
            }
        });
    }
}
