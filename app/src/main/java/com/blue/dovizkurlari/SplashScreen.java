package com.blue.dovizkurlari;

/**
 * Created by Admin on 20.12.2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                new GetContacts().execute();


                Intent mainIntent = new Intent(SplashScreen.this,
                        DovizKurlariActivity.class);
                SplashScreen.this.startActivity(mainIntent);
               SplashScreen.this.finish();


            }
        }, 5000);
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... arg0) {

            //please assume that my Server Handler class is working fine
        /*    JSONParser sh = new JSONParser();

            try {
                Global.jsonList.addAll(sh.getJSONFromUrl());
            }catch (Exception e){

            }*/


            return null;
        }

    }


    public void endSplash(){

        Intent i = new Intent(SplashScreen.this, DovizKurlariActivity.class);
        startActivity(i);

        // close this activity
        finish();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}