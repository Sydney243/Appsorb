package com.example.appsorbv6.custom;


import android.content.Context;
import android.content.DialogInterface;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class CustomAlertDialog {
    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String actionName,
                                  DialogInterface.OnClickListener listener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(actionName, listener)
                .show();

    }

    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String positiveAction,
                                  String negativeAction,
                                  DialogInterface.OnClickListener positiveListener,
                                  DialogInterface.OnClickListener negativeListener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveAction, positiveListener)
                .setNegativeButton(negativeAction, negativeListener)
                .show();

    }

    public static void showDialog(Context context,
                                  String title,
                                  String message,
                                  String positiveAction,
                                  String negativeAction,
                                  String neutralAction,
                                  DialogInterface.OnClickListener positiveListener,
                                  DialogInterface.OnClickListener negativeListener,
                                  DialogInterface.OnClickListener neutralListener) {
        new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveAction, positiveListener)
                .setNegativeButton(negativeAction, negativeListener)
                .setNeutralButton(neutralAction, neutralListener)
                .show();

    }
}
