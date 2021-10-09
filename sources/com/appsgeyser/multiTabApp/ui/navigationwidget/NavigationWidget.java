package com.appsgeyser.multiTabApp.ui.navigationwidget;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.MainNavigationActivity;
import com.appsgeyser.multiTabApp.configuration.UrlBarMenuButton;
import com.appsgeyser.multiTabApp.controllers.WebContentController;
import com.appsgeyser.multiTabApp.storage.BrowsingHistoryItem;
import com.appsgeyser.multiTabApp.storage.BrowsingHistoryStorage;
import com.appsgeyser.multiTabApp.suggestions.SuggestionsClient;
import com.appsgeyser.multiTabApp.suggestions.SuggestionsListener;
import com.appsgeyser.multiTabApp.utils.UserAgentManager;
import com.google.android.gms.plus.PlusShare;
import com.wGhostlySkills_14510784.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public abstract class NavigationWidget implements SuggestionsListener, INavigationWidget {
    protected View _browser = null;
    protected HashMap<String, NavigationWidgetCustomIcon> _customIcons = new HashMap<>();
    protected int _defaultBottomBrowserMargin = 0;
    protected int _defaultLeftBrowserMargin = 0;
    protected int _defaultRightBrowserMargin = 0;
    protected int _defaultTopBrowserMargin = 0;
    protected String _defaultUrl;
    protected boolean _hideOnInternalUrls = false;
    protected BrowsingHistoryStorage _history;
    protected boolean _menuVisible = false;
    protected CompoundButton.OnCheckedChangeListener _onUaChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        }
    };
    protected ViewGroup _parent;
    protected RelativeLayout _progressBarContainer;
    protected View.OnClickListener _refreshOnclickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().reload();
        }
    };
    protected View.OnClickListener _stopOnclickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Factory.getInstance().getTabsController().getSelectedTab().getWebView().stopLoading();
        }
    };
    protected boolean _suggestionsVisible = false;
    protected boolean _visible = true;
    boolean adsKeyboard;
    protected ImageButton clearTextButton = null;
    protected View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View view, boolean z) {
            NavigationWidget.this.inFocus = z;
            NavigationWidget.this.adsKeyboard = Factory.getInstance().getMainNavigationActivity().getAdsKeyboardShow();
            Context context = NavigationWidget.this._parent.getContext();
            if (!z || NavigationWidget.this.adsKeyboard) {
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                NavigationWidget.this.urlTextBox.setSelection(0, 0);
                if (NavigationWidget.this.isHomePageUrl()) {
                    NavigationWidget.this.focusOffUrlBarHomePage();
                } else {
                    NavigationWidget.this.focusOffUrlBarExternalLink();
                }
            } else {
                ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(1, 1);
                if (NavigationWidget.this.isHomePageUrl()) {
                    NavigationWidget.this.focusOnUrlBarHomePage();
                } else {
                    NavigationWidget.this.focusOnUrlBarExternalLink();
                }
            }
        }
    };
    protected Handler handler = new Handler();
    protected boolean inFocus = false;
    protected ViewGroup overlay = null;
    protected ViewGroup overlayScroll = null;
    protected int refreshImage = R.drawable.reload_item;
    protected LinearLayout searchBackground = null;
    protected ImageView searchBtn = null;
    protected ViewGroup searchField = null;
    protected Animation slideDown = null;
    protected Animation slideUp = null;
    protected int stopImage = R.drawable.cross_item;
    protected ImageButton stopRefreshButton = null;
    protected SuggestionsClient suggestionsClient = null;
    protected ViewGroup suggestionsView = null;
    protected ViewGroup suggestionsViewScroll = null;
    protected Toolbar urlBar = null;
    protected int urlBarButton = R.layout.url_bar_menu_button;
    protected int urlBarCheckBox = R.layout.url_bar_menu_checkbox;
    protected int urlBarIcon = R.layout.url_bar_icon;
    protected TextWatcher urlBarTextChangeListener = new TextWatcher() {
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            NavigationWidget.this.onTextChanged();
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            NavigationWidget.this.onTextChanged();
        }

        public void afterTextChanged(Editable editable) {
            NavigationWidget.this.onTextChanged();
            NavigationWidget.this.requestSuggestions();
        }
    };
    AutoCompleteTextView urlTextBox;

    public abstract void hide();

    public abstract void hideAnimated();

    public abstract void hideSuggestionsView();

    public abstract void requestSuggestions();

    public abstract void show();

    public abstract void showAnimated();

    /* access modifiers changed from: protected */
    public void onTextChanged() {
        if (this.inFocus) {
            if (this.urlTextBox.getText().length() <= 0) {
                this.clearTextButton.setVisibility(8);
            } else {
                this.clearTextButton.setVisibility(0);
            }
        }
    }

    public ArrayList<BrowsingHistoryItem> getWeeklyHistory() {
        Cursor loadWeeklyHistory = this._history.loadWeeklyHistory();
        ArrayList<BrowsingHistoryItem> arrayList = new ArrayList<>();
        try {
            if (loadWeeklyHistory.moveToFirst()) {
                do {
                    String string = loadWeeklyHistory.getString(loadWeeklyHistory.getColumnIndex("_id"));
                    String string2 = loadWeeklyHistory.getString(loadWeeklyHistory.getColumnIndex(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE));
                    String string3 = loadWeeklyHistory.getString(loadWeeklyHistory.getColumnIndex("url"));
                    String string4 = loadWeeklyHistory.getString(loadWeeklyHistory.getColumnIndex("visitTime"));
                    if (!isHomePageUrl(string3)) {
                        arrayList.add(new BrowsingHistoryItem(string, string4, string2, string3));
                    }
                } while (loadWeeklyHistory.moveToNext());
            }
            loadWeeklyHistory.close();
            return arrayList;
        } catch (Exception unused) {
            return new ArrayList<>();
        }
    }

    public int removeHistoryItem(long j) {
        return this._history.removeHistoryItemById(j);
    }

    public int removeHistoryAllItem() {
        return this._history.removeHistoryAllItem();
    }

    public void reloadWithChangedUserAgent(boolean z) {
        Context context = this._parent.getContext();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        WebSettings settings = webView.getSettings();
        if (z) {
            settings.setUserAgentString(UserAgentManager.getDesktopUserAgent(context));
        } else {
            settings.setUserAgentString(UserAgentManager.getDefaultUserAgent(context));
        }
        webView.reload();
    }

    public int dpToPx(int i) {
        return (int) (((float) i) * (((float) this._parent.getContext().getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public NavigationWidget(ViewGroup viewGroup, String str, View view, Collection<UrlBarMenuButton> collection) {
    }

    public void onPageStart(WebView webView, String str) {
        hideSuggestionsView();
        ImageButton imageButton = (ImageButton) this._parent.findViewById(R.id.stopRefreshButton);
        imageButton.setImageResource(this.stopImage);
        imageButton.setOnClickListener(this._stopOnclickListener);
        setUrl(str);
        boolean booleanExtra = Factory.getInstance().getMainNavigationActivity().getIntent().getBooleanExtra("focus", false);
        boolean adsKeyboardShow = Factory.getInstance().getMainNavigationActivity().getAdsKeyboardShow();
        this.adsKeyboard = adsKeyboardShow;
        if (this.inFocus || booleanExtra || adsKeyboardShow) {
            if (isHomePageUrl(str)) {
                focusOnUrlBarHomePage();
            } else {
                focusOnUrlBarExternalLink();
            }
        } else if (isHomePageUrl(str)) {
            focusOffUrlBarHomePage();
        } else {
            focusOffUrlBarExternalLink();
        }
    }

    /* access modifiers changed from: protected */
    public void focusOnUrlBarHomePage() {
        final Context context = this._parent.getContext();
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                NavigationWidget.this.hideSuggestionsView();
                NavigationWidget.this.requestSuggestions();
                NavigationWidget.this.searchBackground.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.url_input));
                NavigationWidget.this.searchBtn.setImageResource(R.drawable.ic_search_black_24dp);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 0, NavigationWidget.this.dpToPx(25), 0);
                NavigationWidget.this.urlTextBox.setLayoutParams(layoutParams);
                NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(R.color.urlBarTextDark));
                NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(R.color.urlBarHintDark));
                if (NavigationWidget.this.urlTextBox.getText().length() > 0) {
                    NavigationWidget.this.clearTextButton.setVisibility(0);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void focusOffUrlBarHomePage() {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                NavigationWidget.this.hideSuggestionsView();
                NavigationWidget.this.searchBtn.setImageResource(R.drawable.ic_search_white_24dp);
                NavigationWidget.this.stopRefreshButton.setVisibility(0);
                NavigationWidget.this.clearTextButton.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 0, NavigationWidget.this.dpToPx(0), 0);
                NavigationWidget.this.urlTextBox.setLayoutParams(layoutParams);
                NavigationWidget.this.searchBackground.setBackgroundColor(NavigationWidget.this.getColor(R.color.transparent));
                NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(R.color.urlBarTextLight));
                NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(R.color.urlBarHintLight));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void focusOnUrlBarExternalLink() {
        final Context context = this._parent.getContext();
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                NavigationWidget.this.hideSuggestionsView();
                NavigationWidget.this.requestSuggestions();
                NavigationWidget.this.searchBackground.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.url_input));
                NavigationWidget.this.searchBtn.setImageResource(R.drawable.ic_public_black_24dp);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 0, NavigationWidget.this.dpToPx(25), 0);
                NavigationWidget.this.urlTextBox.setLayoutParams(layoutParams);
                NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(R.color.urlBarTextDark));
                NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(R.color.urlBarHintDark));
                if (NavigationWidget.this.urlTextBox.getText().length() > 0) {
                    NavigationWidget.this.clearTextButton.setVisibility(0);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void focusOffUrlBarExternalLink() {
        final Context context = this._parent.getContext();
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                NavigationWidget.this.hideSuggestionsView();
                NavigationWidget.this.searchBackground.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.url_input));
                NavigationWidget.this.searchBtn.setImageResource(R.drawable.ic_public_black_24dp);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, 0, NavigationWidget.this.dpToPx(0), 0);
                NavigationWidget.this.urlTextBox.setLayoutParams(layoutParams);
                NavigationWidget.this.urlTextBox.setTextColor(NavigationWidget.this.getColor(R.color.urlBarTextDark));
                NavigationWidget.this.urlTextBox.setHintTextColor(NavigationWidget.this.getColor(R.color.urlBarHintDark));
                NavigationWidget.this.clearTextButton.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean isHomePageUrl(String str) {
        if (str == null) {
            return false;
        }
        WebContentController selectedTab = Factory.getInstance().getTabsController().getSelectedTab();
        String str2 = null;
        if (selectedTab.getWidgetInfo() != null) {
            str2 = selectedTab.getWidgetInfo().getLink();
        }
        if (str2 == null) {
            return false;
        }
        return str.toLowerCase().contains(str2.toLowerCase());
    }

    /* access modifiers changed from: protected */
    public boolean isHomePageUrl() {
        WebContentController selectedTab = Factory.getInstance().getTabsController().getSelectedTab();
        return selectedTab.getWebView().getUrl().equalsIgnoreCase(selectedTab.getWidgetInfo().getLink());
    }

    /* access modifiers changed from: private */
    public int getColor(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(this._parent.getContext(), i);
        }
        return this._parent.getContext().getResources().getColor(i);
    }

    public void setHideOnInternalUrls(boolean z) {
        this._hideOnInternalUrls = z;
    }

    /* access modifiers changed from: protected */
    public void initEventHandlers() {
        this.urlTextBox.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return NavigationWidget.this._onUrlTextBoxKeyEvent(view, i, keyEvent);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean _onUrlTextBoxKeyEvent(View view, int i, KeyEvent keyEvent) {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view;
        if (keyEvent.getAction() != 1 || i != 66) {
            return false;
        }
        String obj = autoCompleteTextView.getText().toString();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        webView.loadUrl(obj);
        _setClearButtonVisibility(false);
        webView.requestFocus();
        ((InputMethodManager) this._parent.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
        return true;
    }

    /* access modifiers changed from: protected */
    public void _forceGoToUrl(AutoCompleteTextView autoCompleteTextView) {
        String obj = autoCompleteTextView.getText().toString();
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        webView.loadUrl(obj);
        _setClearButtonVisibility(false);
        webView.requestFocus();
        ((InputMethodManager) this._parent.getContext().getSystemService("input_method")).hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 2);
    }

    public void onHomeButtonClick() {
        WebContentController selectedTab = Factory.getInstance().getTabsController().getSelectedTab();
        selectedTab.getWebView().loadUrl(selectedTab.getWidgetInfo().getLink());
        _setClearButtonVisibility(false);
    }

    public void onAddToStartPageClick() {
        WebContentController selectedTab = Factory.getInstance().getTabsController().getSelectedTab();
        WebView webView = selectedTab.getWebView();
        String link = selectedTab.getWidgetInfo().getLink();
        String url = webView.getUrl();
        if (!url.equals(link)) {
            String title = webView.getTitle();
            if (title == null) {
                title = url;
            }
            Factory.getInstance().getHomePageManager().addBookmark(title, url);
            Toast.makeText(this._parent.getContext(), this._parent.getContext().getString(R.string.new_start_page_item_added), 0).show();
        }
    }

    public void onClickBackButton() {
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        if (webView.canGoBack()) {
            webView.stopLoading();
            webView.goBack();
        }
    }

    public void onClickForwardButton() {
        WebView webView = Factory.getInstance().getTabsController().getSelectedTab().getWebView();
        if (webView.canGoForward()) {
            webView.stopLoading();
            webView.goForward();
        }
    }

    public void initHistory() {
        if (this._history == null) {
            this._history = new BrowsingHistoryStorage(this._parent.getContext());
        }
    }

    public void attachAutocomplete() {
        if (this._history == null) {
            this._history = new BrowsingHistoryStorage(this._parent.getContext());
        }
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this._parent.getContext(), R.layout.history_autocomplete_layout, (Cursor) null, new String[]{PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, "url"}, new int[]{R.id.titleItem, R.id.urlItem});
        simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence charSequence) {
                return NavigationWidget.this._history.getHistoryItemsGroupedByUrl(charSequence != null ? charSequence.toString() : null);
            }
        });
        simpleCursorAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            public String convertToString(Cursor cursor) {
                return cursor.getString(cursor.getColumnIndexOrThrow("url"));
            }
        });
        ((AutoCompleteTextView) this._parent.findViewById(R.id.urlTextbox)).setAdapter(simpleCursorAdapter);
    }

    public void onPageFinished(WebView webView, String str) {
        if (URLUtil.isValidUrl(str)) {
            this._history.addHistoryItem(webView.getTitle(), str, new Date());
            ImageButton imageButton = (ImageButton) this._parent.findViewById(R.id.stopRefreshButton);
            imageButton.setImageResource(this.refreshImage);
            imageButton.setOnClickListener(this._refreshOnclickListener);
        }
        MainNavigationActivity mainNavigationActivity = Factory.getInstance().getMainNavigationActivity();
        if (PreferenceManager.getDefaultSharedPreferences(mainNavigationActivity).getBoolean("show_quick_access_bar", true) && Factory.getInstance().getNavigationWidget() != null && mainNavigationActivity.getIntent().getBooleanExtra("focus", false)) {
            Factory.getInstance().getNavigationWidget().getNawigationWidgetView().findViewById(R.id.urlTextbox).requestFocus();
            mainNavigationActivity.getIntent().removeExtra("focus");
        }
    }

    public ViewGroup getNawigationWidgetView() {
        return this._parent;
    }

    public boolean isVisible() {
        return this._visible;
    }

    public void setUrl(String str) {
        if (this._hideOnInternalUrls) {
            if (_isLocalUrl(str)) {
                hide();
            } else {
                show();
            }
        }
        if (!str.equalsIgnoreCase(this._parent.getContext().getString(R.string.errorHtmlPath))) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) this._parent.findViewById(R.id.urlTextbox);
            if (str.startsWith(this._defaultUrl) || _isLocalUrl(str)) {
                autoCompleteTextView.setText("");
            } else {
                autoCompleteTextView.setText(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _isLocalUrl(String str) {
        return str.startsWith("file://");
    }

    public HashMap<String, NavigationWidgetCustomIcon> getCustomIcons() {
        return this._customIcons;
    }

    private void _setClearButtonVisibility(final boolean z) {
        Factory.getInstance().getMainNavigationActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (NavigationWidget.this.clearTextButton != null) {
                    NavigationWidget.this.clearTextButton.setVisibility(z ? 0 : 8);
                }
            }
        });
    }
}
