package com.appsgeyser.multiTabApp.ui.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.appsgeyser.multiTabApp.Factory;
import com.appsgeyser.multiTabApp.pdfreader.PdfLoader;
import com.github.barteksc.pdfviewer.PDFView;
import com.wGhostlySkills_14510784.R;

public class PdfActivity extends AppCompatActivity {
    private PdfLoader pdfLoader;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pdf_content);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Factory.getInstance().getWidgetsController().getWidgetByTabId(getIntent().getStringExtra("widget_id")).getName());
        }
        this.pdfLoader = new PdfLoader((PDFView) findViewById(R.id.pdfView), getIntent().getStringExtra("path_file"), (PdfLoader.PdfEventListener) null, Factory.getInstance().getTabsController().getSelectedTab().getWidgetInfo().getTabId());
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 112);
        } else {
            this.pdfLoader.loadPdfFile();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 112 && iArr.length > 0 && iArr[0] == 0) {
            this.pdfLoader.loadPdfFile();
        }
    }
}
