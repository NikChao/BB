package com.example.nik.bbfb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;

import com.firebase.client.Firebase;

import java.util.jar.Attributes;

public class MainActivity extends Activity {

    //TextView fireData;
    Firebase memRef;
    //private static String url = "https://dazzling-fire-538.firebaseio.com/";
    //private static String User_Name;
    //private static String ID = "004";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                goToLogin();
            }
        });*/

        if(isInternet()) {
            goToLogin();
        } else {
            NoNetDialogue noNet = new NoNetDialogue();

        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
   /* public static String queryUser(String query) {

        String overallQuery = "User/" + ID + "/" + query;
        return overallQuery;
    }*/

    /**
     * checks if settings are default
     * @return true if default, else false
     */

    /*
    public void setSettings(Context context) {
        SharedPreferences myPrefs = context.getSharedPreferences(getString(R.string.User_ID), MODE_PRIVATE);
        ID = (String) myPrefs.getString("USER_ID", null);
        User_Name = (String) myPrefs.getString("Name", null);

    }
*/

    public void goToLogin() {
        Intent intent = new Intent("com.example.nik.bbfb.LoginPage");
        startActivity(intent);
    }

    public boolean isInternet() {

        //TODO replace derprecated method getNetworkInfo
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
            || (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)) {
            connected = true;
        } else connected = false;
        return connected;
    }

}
