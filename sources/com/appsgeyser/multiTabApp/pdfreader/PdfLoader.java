package com.appsgeyser.multiTabApp.pdfreader;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appsgeyser.multiTabApp.Factory;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.mopub.common.Constants;
import com.wGhostlySkills_14510784.R;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PdfLoader {
    /* access modifiers changed from: private */
    public static String savedPageKey = "";
    /* access modifiers changed from: private */
    public TextView errorTextView;
    private String initialTabId;
    private String pathFile;
    /* access modifiers changed from: private */
    public PdfEventListener pdfEventListener;
    /* access modifiers changed from: private */
    public PDFView pdfView;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public SharedPreferences sharedPreferences;

    public interface PdfEventListener {
        void handleEvent();
    }

    public PdfLoader(PDFView pDFView, String str, PdfEventListener pdfEventListener2, String str2) {
        this.pdfView = pDFView;
        this.pathFile = str;
        this.initialTabId = str2;
        this.pdfEventListener = pdfEventListener2;
    }

    public void loadPdfFile() {
        RelativeLayout relativeLayout = (RelativeLayout) this.pdfView.getParent();
        this.errorTextView = (TextView) relativeLayout.findViewById(R.id.error_text_view);
        this.progressBar = (ProgressBar) relativeLayout.findViewById(R.id.pdf_progress_bar);
        this.sharedPreferences = Factory.getInstance().getMainNavigationActivity().getSharedPreferences("AppsgeyserPrefs", 0);
        savedPageKey = "pdf_saved_page_" + this.initialTabId;
        PDFView.Configurator configurator = null;
        if (this.pathFile.startsWith("file:///")) {
            configurator = this.pdfView.fromAsset(this.pathFile.replace("file:///android_asset/", ""));
        } else if (this.pathFile.startsWith(Constants.HTTP)) {
            String str = this.pathFile;
            new LoaderPdf().execute(new String[]{str});
        }
        try {
            this.pdfView.useBestQuality(true);
            if (configurator != null) {
                loadConfigurator(configurator);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.progressBar.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void loadConfigurator(PDFView.Configurator configurator) {
        String str = "pdf_saved_page_" + this.initialTabId;
        savedPageKey = str;
        int i = this.sharedPreferences.getInt(str, 0);
        this.pdfView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PdfLoader.this.pdfEventListener != null) {
                    PdfLoader.this.pdfEventListener.handleEvent();
                }
            }
        });
        configurator.enableSwipe(true).enableDoubletap(true).defaultPage(i).onDraw((OnDrawListener) null).onLoad(new OnLoadCompleteListener() {
            public void loadComplete(int i) {
                PdfLoader.this.progressBar.setVisibility(8);
            }
        }).onPageChange(new OnPageChangeListener() {
            public void onPageChanged(int i, int i2) {
                String tabId = Factory.getInstance().getTabsController().getSelectedTab().getWidgetInfo().getTabId();
                String unused = PdfLoader.savedPageKey = "pdf_saved_page_" + tabId;
                PdfLoader.this.sharedPreferences.edit().putInt(PdfLoader.savedPageKey, i).apply();
                if (PdfLoader.this.pdfEventListener != null) {
                    PdfLoader.this.pdfEventListener.handleEvent();
                }
            }
        }).onError(new OnErrorListener() {
            public void onError(Throwable th) {
                PdfLoader.this.progressBar.setVisibility(8);
                PdfLoader.this.errorTextView.setVisibility(0);
                PdfLoader.this.pdfView.setVisibility(8);
                th.printStackTrace();
            }
        }).scrollHandle(new DefaultScrollHandle(this.pdfView.getContext())).enableAnnotationRendering(false).password((String) null).load();
    }

    private class LoaderPdf extends AsyncTask<String, Void, InputStream> {
        private LoaderPdf() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            PdfLoader.this.progressBar.setVisibility(0);
        }

        /* access modifiers changed from: protected */
        public InputStream doInBackground(String... strArr) {
            try {
                return new URL(strArr[0]).openStream();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(InputStream inputStream) {
            super.onPostExecute(inputStream);
            PdfLoader.this.loadConfigurator(PdfLoader.this.pdfView.fromStream(inputStream));
        }
    }
}
