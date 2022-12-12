package com.mike.todolist.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

    public static void makeToast(Context context, CharSequence text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void makeShortToast(Context context, CharSequence text) {
        makeToast(context, text, Toast.LENGTH_SHORT);
    }
}
