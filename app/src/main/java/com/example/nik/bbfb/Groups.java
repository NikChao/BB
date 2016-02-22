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
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Groups extends Activity {

    String myGrStr = "";

    String[] myGroupIDs;
    String[] myGroups;
    TextView[] groupButtons;
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
                groupButtons = new Button[myGrCount];
                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("Members").hasChild(User.getID())) {
                        myGroups[i] = ds.child("Name").getValue().toString();
                        myGrStr = myGrStr.concat(myGroups[i] + "\n");
                        i++;
                    }
                }
                //groupView.setText(myGrStr);
                myGrStr = "";

                for (i = 0; i < myGroups.length; i++) {
                    gl = (TableRow)findViewById(R.id.dynamicButtonLayout);
                    glp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                    groupButtons[i] = new Button(Groups.this);
                    groupButtons[i].setText(myGroups[i]);
                    gl.addView(groupButtons[i], glp);
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

    public void openGroup(String groupID, String groupName, String[] groupMembers) {
        Intent intent = new Intent("com.example.nik.bbfb.IndividualGroup");
        Bundle groupBundle = new Bundle();
        groupBundle.putString("groupID", groupID);
        groupBundle.putString("groupName", groupName);
        groupBundle.putStringArray("groupMembers", groupMembers);
        intent.putExtra("groupBundle", groupBundle);
        startActivity(intent);
        finish();
    }

}