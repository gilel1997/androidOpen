package com.example.worknumtow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class MyDialogDelete extends DialogFragment
{
    private ArrayList<Item> items;
    private Item product;

    public static MyDialogDelete newInstance(ArrayList<Item> items, Item product ) {
        MyDialogDelete fragment = new MyDialogDelete();
        Bundle args = new Bundle();
        args.putParcelable("items", (Parcelable) items);
        args.putParcelable("product", (Parcelable) product);
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete item")
                .setMessage("are you sure you want to delete this item?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "delete successful", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < items.size(); i ++)
                        {
                            if(items.get(i).itemName == product.itemName)
                            {
                                items.remove(items.get(i));
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
