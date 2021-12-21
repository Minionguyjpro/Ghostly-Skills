package com.appsgeyser.multiTabApp;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.appsgeyser.multiTabApp.browser.DownloadsItem;
import com.appsgeyser.multiTabApp.browser.DownloadsItemAdapter;
import com.appsgeyser.multiTabApp.storage.DatabaseOpenHelper;
import com.appsgeyser.multiTabApp.utils.ThemeUtils;
import com.google.android.gms.plus.PlusShare;
import com.wGhostlySkills_14510784.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DownloadsListActivity extends AppCompatActivity {
    public static final String DOWNLOAD_LIST_MSG = DownloadsListActivity.class.toString();
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    private final int IDM_DELETE = 1;
    private DatabaseOpenHelper _dbHelper;
    BroadcastReceiver activityReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("type", 0);
            if (intExtra == 100) {
                synchronized (DownloadsListActivity.lock) {
                    if (DownloadsListActivity.this.myTimer == null) {
                        Timer unused = DownloadsListActivity.this.myTimer = new Timer();
                        DownloadsListActivity.this.myTimer.schedule(new ProgressTask(), 0, 100);
                    }
                    DownloadsListActivity.this.itemList.add((DownloadsItem) intent.getSerializableExtra("item"));
                    Collections.sort(DownloadsListActivity.this.itemList, DownloadsListActivity.this.comparator);
                    DownloadsListActivity.this.downloadsItemAdapter.notifyDataSetChanged();
                }
            }
            if (intExtra == 101) {
                synchronized (DownloadsListActivity.lock) {
                    DownloadsListActivity.this.itemList.clear();
                    DownloadsListActivity.this.itemList.addAll(DownloadsListActivity.this.getItemList());
                    DownloadsListActivity.this.downloadsItemAdapter.notifyDataSetChanged();
                }
            }
        }
    };
    Comparator<DownloadsItem> comparator = new Comparator<DownloadsItem>() {
        public int compare(DownloadsItem downloadsItem, DownloadsItem downloadsItem2) {
            int compareTo;
            if (downloadsItem.getStatus() == DownloadsItem.Status.InProgress && downloadsItem2.getStatus() == DownloadsItem.Status.InProgress) {
                compareTo = Long.valueOf(downloadsItem.getId_d()).compareTo(Long.valueOf(downloadsItem2.getId_d()));
            } else if (downloadsItem.getStatus() == DownloadsItem.Status.InProgress) {
                return -1;
            } else {
                if (downloadsItem2.getStatus() == DownloadsItem.Status.InProgress) {
                    return 1;
                }
                compareTo = Long.valueOf(downloadsItem.getId_d()).compareTo(Long.valueOf(downloadsItem2.getId_d()));
            }
            return compareTo * -1;
        }
    };
    DownloadManager downloadManager;
    /* access modifiers changed from: private */
    public DownloadsItemAdapter downloadsItemAdapter;
    /* access modifiers changed from: private */
    public List<DownloadsItem> itemList;
    /* access modifiers changed from: private */
    public Timer myTimer;
    @BindView
    Toolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ThemeUtils.setCurrentThemeWithNoActionBar(this);
        setContentView((int) R.layout.activity_downloads_list);
        ButterKnife.bind(this);
        ListView listView = (ListView) findViewById(R.id.downloadsList);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(-1, -1));
        textView.setGravity(17);
        textView.setText(getResources().getString(R.string.text_list_is_empty));
        ((ViewGroup) listView.getParent()).addView(textView);
        listView.setEmptyView(textView);
        this.toolbar.setTitle((int) R.string.downloads_list);
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.downloadsItemAdapter = new DownloadsItemAdapter(this, R.layout.downloads_item, this.itemList);
        registerForContextMenu(listView);
        this.downloadManager = (DownloadManager) getSystemService("download");
        this.itemList = getItemList();
        registerReceiver(this.activityReceiver, new IntentFilter(DOWNLOAD_LIST_MSG));
        this.downloadsItemAdapter = new DownloadsItemAdapter(this, R.layout.downloads_item, this.itemList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MimeTypeMap singleton = MimeTypeMap.getSingleton();
                Intent intent = new Intent("android.intent.action.VIEW");
                DownloadsListActivity downloadsListActivity = DownloadsListActivity.this;
                String mimeTypeFromExtension = singleton.getMimeTypeFromExtension(downloadsListActivity.fileExt(((DownloadsItem) downloadsListActivity.itemList.get(i)).getFile_path()));
                intent.setFlags(268435456);
                if (Build.VERSION.SDK_INT >= 24) {
                    File file = new File(((DownloadsItem) DownloadsListActivity.this.itemList.get(i)).getFile_path());
                    DownloadsListActivity downloadsListActivity2 = DownloadsListActivity.this;
                    Uri uriForFile = FileProvider.getUriForFile(downloadsListActivity2, DownloadsListActivity.this.getApplicationContext().getPackageName() + ".fileprovider", file);
                    intent.addFlags(1073741824);
                    intent.addFlags(1);
                    intent.setDataAndType(uriForFile, mimeTypeFromExtension);
                } else {
                    intent.setDataAndType(Uri.parse("file://" + ((DownloadsItem) DownloadsListActivity.this.itemList.get(i)).getFile_path()), mimeTypeFromExtension);
                    intent.setFlags(268435456);
                }
                try {
                    DownloadsListActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(DownloadsListActivity.this, R.string.no_handler, 1).show();
                }
            }
        });
        listView.setAdapter(this.downloadsItemAdapter);
        Timer timer = new Timer();
        this.myTimer = timer;
        timer.schedule(new ProgressTask(), 0, 100);
    }

    /* access modifiers changed from: private */
    public String fileExt(String str) {
        if (str == null) {
            return null;
        }
        if (str.contains("?")) {
            str = str.substring(0, str.indexOf("?"));
        }
        if (str.lastIndexOf(".") == -1) {
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        if (substring.contains("%")) {
            substring = substring.substring(0, substring.indexOf("%"));
        }
        if (substring.contains("/")) {
            substring = substring.substring(0, substring.indexOf("/"));
        }
        return substring.toLowerCase();
    }

    private class ProgressTask extends TimerTask {
        private ProgressTask() {
        }

        public void run() {
            if ((DownloadsListActivity.this.itemList.size() == 0 || !((DownloadsItem) DownloadsListActivity.this.itemList.get(0)).getStatus().equals(DownloadsItem.Status.InProgress)) && DownloadsListActivity.this.myTimer != null) {
                DownloadsListActivity.this.myTimer.cancel();
                Timer unused = DownloadsListActivity.this.myTimer = null;
                return;
            }
            synchronized (DownloadsListActivity.lock) {
                Iterator it = DownloadsListActivity.this.itemList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    DownloadsItem downloadsItem = (DownloadsItem) it.next();
                    if (downloadsItem.getStatus() != DownloadsItem.Status.InProgress) {
                        break;
                    }
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(new long[]{downloadsItem.getId_d()});
                    Cursor query2 = DownloadsListActivity.this.downloadManager.query(query);
                    if (query2.moveToFirst()) {
                        query2.moveToFirst();
                        int i = query2.getInt(query2.getColumnIndex("bytes_so_far"));
                        int i2 = query2.getInt(query2.getColumnIndex("total_size"));
                        query2.close();
                        downloadsItem.setProgress(Integer.valueOf((i * 100) / i2));
                    } else {
                        downloadsItem.setStatus(DownloadsItem.Status.Fail);
                        downloadsItem.setDescription(DownloadsItem.Status.Fail.toString());
                        downloadsItem.setDate(Long.valueOf(System.currentTimeMillis()));
                        downloadsItem.setFile_path((String) null);
                    }
                    DownloadsListActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            DownloadsListActivity.this.downloadsItemAdapter.notifyDataSetChanged();
                        }
                    });
                    query2.close();
                }
            }
        }
    }

    public void delete(int i) {
        long id_d = this.itemList.get(i).getId_d();
        SQLiteDatabase writableDatabase = this._dbHelper.getWritableDatabase();
        writableDatabase.delete("downloadsList", "id_d = " + id_d, (String[]) null);
        writableDatabase.close();
        this.itemList.remove(i);
        this.downloadsItemAdapter.notifyDataSetChanged();
    }

    public void delete() {
        this.itemList.clear();
        SQLiteDatabase writableDatabase = this._dbHelper.getWritableDatabase();
        writableDatabase.delete("downloadsList", (String) null, (String[]) null);
        writableDatabase.close();
    }

    public List<DownloadsItem> getItemList() {
        this._dbHelper = new DatabaseOpenHelper(this);
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = this._dbHelper.getWritableDatabase();
        Cursor query = writableDatabase.query("downloadsList", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("name");
            int columnIndex2 = query.getColumnIndex(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
            int columnIndex3 = query.getColumnIndex("id_d");
            int columnIndex4 = query.getColumnIndex("link_d");
            int columnIndex5 = query.getColumnIndex("file_path");
            int columnIndex6 = query.getColumnIndex("date");
            do {
                DownloadsItem downloadsItem = new DownloadsItem(query.getString(columnIndex));
                downloadsItem.setDescription(query.getString(columnIndex2));
                downloadsItem.setFile_path(query.getString(columnIndex5));
                downloadsItem.setId_d(query.getLong(columnIndex3));
                downloadsItem.setLink_d(query.getString(columnIndex4));
                downloadsItem.setDate(Long.valueOf(query.getLong(columnIndex6)));
                DownloadManager.Query query2 = new DownloadManager.Query();
                query2.setFilterById(new long[]{downloadsItem.getId_d()});
                Cursor query3 = this.downloadManager.query(query2);
                if (query3.moveToFirst()) {
                    int columnIndex7 = query3.getColumnIndex("status");
                    int columnIndex8 = query3.getColumnIndex("last_modified_timestamp");
                    int i = query3.getInt(columnIndex7);
                    int i2 = query3.getInt(query3.getColumnIndex("total_size"));
                    if (i == 2) {
                        downloadsItem.setStatus(DownloadsItem.Status.InProgress);
                        downloadsItem.setProgress(Integer.valueOf((query3.getInt(query3.getColumnIndex("bytes_so_far")) * 100) / i2));
                    } else if (i == 8) {
                        downloadsItem.setStatus(DownloadsItem.Status.Ok);
                        if (downloadsItem.getDate() == null || downloadsItem.getDate().longValue() == 0) {
                            downloadsItem.setDate(Long.valueOf(query3.getLong(columnIndex8)));
                        }
                    } else if (i == 16) {
                        downloadsItem.setStatus(DownloadsItem.Status.Fail);
                        if (downloadsItem.getDate() == null || downloadsItem.getDate().longValue() == 0) {
                            downloadsItem.setDate(Long.valueOf(query3.getLong(columnIndex8)));
                        }
                    }
                }
                arrayList.add(downloadsItem);
                query3.close();
            } while (query.moveToNext());
        }
        query.close();
        writableDatabase.close();
        Collections.sort(arrayList, this.comparator);
        return arrayList;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        try {
            contextMenu.setHeaderTitle(this.itemList.get(((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position).getName());
            contextMenu.add(0, 1, 0, R.string.delete);
        } catch (ClassCastException e) {
            Log.e("onCreateContextMenu", "bad menuInfo", e);
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int i = ((AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo()).position;
        if (menuItem.getItemId() == 1) {
            synchronized (lock) {
                delete(i);
            }
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.downloads_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
            return true;
        } else if (itemId != R.id.delete_all_item) {
            return false;
        } else {
            delete();
            this.downloadsItemAdapter.notifyDataSetChanged();
            return true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.activityReceiver);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }
}
