package com.example.worknumtow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class MyDialogShow extends DialogFragment
{
    private ArrayList<Item> items;
    public static MyDialogShow newInstance(ArrayList<Item> items) {
        MyDialogShow fragment = new MyDialogShow();
        Bundle args = new Bundle();
        args.putParcelable("items", (Parcelable) items);
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("show list")
                .setMessage(items.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }
}
