package com.github.barteksc.pdfviewer;

import android.content.Context;
import android.os.AsyncTask;
import com.github.barteksc.pdfviewer.source.DocumentSource;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

class DecodingAsyncTask extends AsyncTask<Void, Void, Throwable> {
    private boolean cancelled = false;
    private Context context;
    private DocumentSource docSource;
    private String password;
    private PdfDocument pdfDocument;
    private PDFView pdfView;
    private PdfiumCore pdfiumCore;

    public DecodingAsyncTask(DocumentSource documentSource, String str, PDFView pDFView, PdfiumCore pdfiumCore2) {
        this.docSource = documentSource;
        this.pdfView = pDFView;
        this.password = str;
        this.pdfiumCore = pdfiumCore2;
        this.context = pDFView.getContext();
    }

    /* access modifiers changed from: protected */
    public Throwable doInBackground(Void... voidArr) {
        try {
            this.pdfDocument = this.docSource.createDocument(this.context, this.pdfiumCore, this.password);
            return null;
        } catch (Throwable th) {
            return th;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Throwable th) {
        if (th != null) {
            this.pdfView.loadError(th);
        } else if (!this.cancelled) {
            this.pdfView.loadComplete(this.pdfDocument);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        this.cancelled = true;
    }
}
