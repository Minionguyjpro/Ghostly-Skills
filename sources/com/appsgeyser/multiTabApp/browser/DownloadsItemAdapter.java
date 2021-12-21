package com.appsgeyser.multiTabApp.browser;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.appsgeyser.multiTabApp.browser.DownloadsItem;
import com.wGhostlySkills_14510784.R;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DownloadsItemAdapter extends ArrayAdapter<DownloadsItem> {
    private Activity context;
    private List<DownloadsItem> itemList;
    private int resource;

    public DownloadsItemAdapter(Activity activity, int i, List<DownloadsItem> list) {
        super(activity, i, list);
        this.itemList = list;
        this.context = activity;
        this.resource = i;
    }

    private static class ViewHolder {
        TextView date;
        TextView description;
        TextView name;
        ProgressBar progressBar;

        private ViewHolder() {
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.context.getLayoutInflater().inflate(this.resource, (ViewGroup) null, true);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.nameDI);
            viewHolder.description = (TextView) view.findViewById(R.id.descriptionDI);
            viewHolder.progressBar = (ProgressBar) view.findViewById(R.id.progressBarDI);
            viewHolder.date = (TextView) view.findViewById(R.id.time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DownloadsItem downloadsItem = this.itemList.get(i);
        viewHolder.name.setText(downloadsItem.getName());
        viewHolder.description.setText(downloadsItem.getDescription());
        if (downloadsItem.getDate() == null || downloadsItem.getDate().longValue() == 0) {
            viewHolder.date.setVisibility(8);
        } else {
            if (isSameDay(new Date(), new Date(downloadsItem.getDate().longValue()))) {
                viewHolder.date.setText(DateFormat.format("HH:mm", downloadsItem.getDate().longValue()));
            } else {
                viewHolder.date.setText(DateFormat.format("dd MMM", downloadsItem.getDate().longValue()));
            }
            viewHolder.date.setVisibility(0);
        }
        if (downloadsItem.getStatus() == DownloadsItem.Status.InProgress) {
            viewHolder.progressBar.setProgress(downloadsItem.getProgress().intValue());
            viewHolder.progressBar.setVisibility(0);
            viewHolder.description.setVisibility(8);
        } else if (downloadsItem.getStatus() == DownloadsItem.Status.Ok) {
            viewHolder.progressBar.setVisibility(8);
            viewHolder.description.setVisibility(0);
        } else if (downloadsItem.getStatus() == DownloadsItem.Status.Fail) {
            viewHolder.progressBar.setVisibility(8);
            viewHolder.description.setVisibility(0);
        }
        return view;
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return isSameDay(instance, instance2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) {
            return true;
        } else {
            return false;
        }
    }
}
