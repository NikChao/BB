package com.example.nik.bbfb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by nik on 22/02/2016.
 */
public class NoNetDialogue extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("No internet :( \n Turn on Internet and try again")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int d) {
                        //TODO kill dialog and restart MainActivity
                    }
                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int d) {
                //TODO kill dialog and app
            }
        });


        return builder.create();
    }


}