package com.appsgeyser.multiTabApp.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appsgeyser.multiTabApp.Factory;
import com.wGhostlySkills_14510784.R;

public class SimpleDialogs {
    public static AlertDialog createConfirmDialog(String str, String str2, Context context, final DialogInterface.OnClickListener onClickListener, final DialogInterface.OnClickListener onClickListener2) {
        final AlertDialog create = new AlertDialog.Builder(context).create();
        LinearLayout linearLayout = (LinearLayout) Factory.getInstance().getMainNavigationActivity().getLayoutInflater().inflate(R.layout.dialog_2_buttons, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.dialog_message);
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.dialog_positive_button);
        TextView textView3 = (TextView) linearLayout.findViewById(R.id.dialog_negative_button);
        TextView textView4 = (TextView) linearLayout.findViewById(R.id.dialog_title);
        create.setView(linearLayout);
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        textView.setText(str2);
        if (str != null) {
            textView4.setText(str);
        } else {
            textView4.setVisibility(8);
        }
        textView2.setText(context.getString(R.string.yes));
        textView3.setText(context.getString(R.string.no));
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
                DialogInterface.OnClickListener onClickListener = onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(create, 0);
                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
                DialogInterface.OnClickListener onClickListener = onClickListener2;
                if (onClickListener != null) {
                    onClickListener.onClick(create, 0);
                }
            }
        });
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                DialogInterface.OnClickListener onClickListener = onClickListener2;
                if (onClickListener != null) {
                    onClickListener.onClick(dialogInterface, 0);
                }
            }
        });
        return create;
    }

    public static void showOpenOrSaveDialog(Context context, final DialogInterface.OnClickListener onClickListener, final DialogInterface.OnClickListener onClickListener2) {
        final AlertDialog create = new AlertDialog.Builder(context).create();
        LinearLayout linearLayout = (LinearLayout) Factory.getInstance().getMainNavigationActivity().getLayoutInflater().inflate(R.layout.dialog_3_buttons, (ViewGroup) null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.dialog_message);
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.dialog_positive_button);
        TextView textView3 = (TextView) linearLayout.findViewById(R.id.dialog_negative_button);
        TextView textView4 = (TextView) linearLayout.findViewById(R.id.dialog_neutral_button);
        TextView textView5 = (TextView) linearLayout.findViewById(R.id.dialog_title);
        create.setView(linearLayout);
        if (create.getWindow() != null) {
            create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        textView2.setText(context.getString(R.string.open_file));
        textView3.setText(context.getString(R.string.save_file));
        textView4.setText(context.getString(R.string.cancel));
        textView5.setText(context.getString(R.string.loadingPaneTitle));
        textView.setText(context.getString(R.string.dialog_select_action));
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(create, 0);
                create.cancel();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener2.onClick(create, 0);
                create.cancel();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.cancel();
            }
        });
        create.setCancelable(true);
        create.show();
    }
}
