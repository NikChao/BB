package com.example.nik.bbfb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class IndividualGroup extends AppCompatActivity {

    //private static String name;
    //private static String groupID;
    //private static String[] members;
    //private static int[] memberDebt;
    //public final String url = "https://dazzling-fire-538.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Bundle groupBundle = getIntent().getExtras();
        name = groupBundle.getString("groupName");
        groupID = groupBundle.getString("groupID");
        members = groupBundle.getStringArray("groupMembers");
        memberDebt = new int[members.length];*/

   /*     Firebase memRef = new Firebase(url);
        memRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.child("Group").child(groupID)
                        .child("Members").getChildren()) {
                    String Str = new String();
                    int memberIndex = Str.indexOf((String) ds.getKey());
                    memberDebt[memberIndex] = (int) ds.getValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

}
