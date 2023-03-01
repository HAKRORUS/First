package com.example.tableofshulte;

import android.widget.TextView;

import java.util.ArrayList;

public class red {
    public static String access = "0";

    private static ArrayList<String> arrayList = new ArrayList<>();
    private static ArrayList<TextView> textViews = new ArrayList<>();

    public static ArrayList<String> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<String> arrayList) {
        red.arrayList = arrayList;
    }

    public static ArrayList<TextView> getTextViews() {
        return textViews;
    }

    public static void setTextViews(ArrayList<TextView> textViews) {
        red.textViews = textViews;
    }
    public static String getAccess() {
        return access;
    }

    public static void setAccess(String access) {
        red.access = access;
    }
}
