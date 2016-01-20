package com.blue.dovizkurlari;

import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import com.blue.model.JsonModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class DovizKurlariActivity extends AppCompatActivity  {

    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doviz_kurlari);


        AdView adView = (AdView) this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest); //adView i yüklüyoruz

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Global.jsonList=new ArrayList<JsonModel>();

        try {
            JSONParser PAR=new JSONParser();
            Global.jsonList.addAll( PAR.getJSONFromUrl());


        }catch (Exception e){

        }



        //TODO tab menu
        TabHost tabhost = (TabHost) findViewById(R.id.tabHost);

         mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabhost.setup(mLocalActivityManager);




        TabHost.TabSpec altinSpec = tabhost.newTabSpec("ALTIN");
        altinSpec.setIndicator("ALTIN");
        Intent altinIntent = new Intent(this, AltinActivity.class);
        altinSpec.setContent(altinIntent);



        TabHost.TabSpec dolarSpec = tabhost.newTabSpec("DOLAR");
        dolarSpec.setIndicator("DOLAR");
        Intent dolarIntent = new Intent(this, DolarActivity.class);
        dolarSpec.setContent(dolarIntent);

        TabHost.TabSpec euroSpec = tabhost.newTabSpec("EURO");
        euroSpec.setIndicator("EURO");
        Intent oeuroIntent = new Intent(this, EuroActivity.class);
        euroSpec.setContent(oeuroIntent);


        tabhost.addTab(altinSpec);
        tabhost.addTab(dolarSpec);
        tabhost.addTab(euroSpec);


    }

    @Override
    protected void onResume(){
        super.onResume();
        mLocalActivityManager.dispatchResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mLocalActivityManager.dispatchPause(isFinishing());
    }

    public  void altinChange(){
        System.out.println("altin change");
    }


    public  void dolarChange(){
        System.out.println("dolar change");
    }

    public  void euroChange(){
        System.out.println("euro change");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doviz_kurlari, menu);
        getSupportActionBar().setLogo(R.drawable.icon);
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
            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

            // set the message to display
            alertbox.setIcon(R.drawable.icon);
            alertbox.setTitle(" Blueyazılım ");
            alertbox.setMessage("Şikayet ve Önerileriniz için : info@blueyazilim.com ");

            // add a neutral button to the alert box and assign a click listener
            alertbox.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {

                // click listener on the alert box
                public void onClick(DialogInterface arg0, int arg1) {
                    // the button was clicked

                }
            });

            // show it
            alertbox.show();
        }

        return true;
    }
}
