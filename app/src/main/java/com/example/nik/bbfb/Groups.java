package com.example.nik.bbfb;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Groups extends Activity {

    String myGrStr = "";
    private static boolean gotData = false;

    String[] myGroupIDs;
    String[] myGroups;
    static TableRow gl;
    static TableRow.LayoutParams glp;
    final static String url = "https://dazzling-fire-538.firebaseio.com/Group/";
    public static int myGrCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        Firebase memRef = new Firebase(url);
        memRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int grCount = (int) dataSnapshot.getChildrenCount();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //myGrCount++;
                    if (ds.child("Members").hasChild(User.getID())) {
                        myGrCount++;
                    }
                }
                //groupView = (TextView) findViewById(R.id.groupRL);
                myGroups = new String[myGrCount];
                myGroupIDs = new String[myGrCount];
                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("Members").hasChild(User.getID())) {
                        myGroups[i] = ds.child("Name").getValue().toString();
                        myGroupIDs[i] = ds.child("ID").getValue().toString();
                        myGrStr = myGrStr.concat(myGroups[i] + "\n");
                        i++;
                    }
                }
                //groupView.setText(myGrStr);
                myGrStr = "";

                if(!gotData) {
                    generateButtons();
                    gotData = true;
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "HOME", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


    }

    public void generateButtons() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.tt);
        for(int i = 0; i < myGroups.length; i++) {
            final int index = i;
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setId(i);
            btn.setText(myGroups[i]);

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    openGroup(myGroupIDs[index], myGroups[index]);
                }
            });
            ll.addView(btn);
        }
    }

    public void openGroup(String groupID, String groupName) {
        Intent intent = new Intent("com.example.nik.bbfb.IndividualGroup");
        Bundle groupBundle = new Bundle();
        groupBundle.putString("groupID", groupID);
        groupBundle.putString("groupName", groupName);
        //groupBundle.putStringArray("groupMembers", groupMembers);
        intent.putExtra("groupBundle", groupBundle);
        startActivity(intent);
        finish();
    }

}
