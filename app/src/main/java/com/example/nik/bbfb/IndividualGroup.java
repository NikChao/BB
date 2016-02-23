package com.example.nik.bbfb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class IndividualGroup extends AppCompatActivity {

    private static String thisName;
    private static String thisGroupID;
    private static String[] theseMembers;
    private static int[] theseMemberDebts;
    private static boolean gotData = false;
    public final String url = "https://dazzling-fire-538.firebaseio.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle groupBundle = getIntent().getExtras();
        thisName = groupBundle.getString("groupName");
        thisGroupID = groupBundle.getString("groupID");
        //theseMembers = getMembers(thisName, thisGroupID);
        //theseMemberDebts = new int[theseMembers.length];


        //TODO fix up this dataframe->generateButtons (use Groups as reference)
        /*Firebase memRef = new Firebase(url + "/" +thisGroupID + "/Group");
        memRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                int numMembers = (int) dataSnapshot.child("Member").getChildrenCount();
                theseMembers = new String[numMembers];
                theseMemberDebts = new int[numMembers];
                for(DataSnapshot ds : dataSnapshot.child("Member").getChildren()) {
                    theseMembers[i] = ds.getKey().toString();
                    i++;
                }

                if(!gotData) {
                    generateButtons();
                    gotData = true;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void generateButtons() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.igtt);
        for(int i = 0; i < 3; i++) {

            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setId(i);
            btn.setText(theseMembers[i]);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            ll.addView(btn);
        }
    }

    /**public void sendBitchPing(String toID) {
        Random rnd = new Random();
        int i;
        String msgID;
        i = rnd.nextInt();
        String fromID = User.getID();
        Firebase bitchPing = new Firebase("https://dazzling-fire-538.firebaseio.com/Message");
        while(bitchPing.child(null).equals(Integer.toString(i))) {
            i = rnd.nextInt();
        }
        msgID = Integer.toString(i);
        bitchPing = new Firebase("https://dazzling-fire-538.firebaseio.com/Message/" + msgID);
        bitchPing.child("To").setValue(toID);
        bitchPing.child("From").setValue(fromID);

    }

    public String queryURL(String query) {

        return url.concat("Group/").concat(query);
    }
    */

    public String[] getMembers(String n, String id) {

        String[] members = {"test", "test2", "test3"};

        return members;
    }

}
