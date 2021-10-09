package com.appsgeyser.multiTabApp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import com.appsgeyser.multiTabApp.pdfreader.PdfLoader;
import com.github.barteksc.pdfviewer.PDFView;
import com.wGhostlySkills_14510784.R;

public class PdfContent extends TabContent {
    public PdfContent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PdfContent(Context context) {
        super(context);
    }

    public void init(String str, PdfLoader.PdfEventListener pdfEventListener, String str2) {
        new PdfLoader((PDFView) findViewById(R.id.pdfView), str, pdfEventListener, str2).loadPdfFile();
    }
}
