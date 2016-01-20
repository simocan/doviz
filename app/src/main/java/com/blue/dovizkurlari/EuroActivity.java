package com.blue.dovizkurlari;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.blue.model.JsonModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 3.1.2016.
 */
public class EuroActivity extends ListActivity {

    ArrayList<HashMap<String, String>> inboxList=new ArrayList<HashMap<String, String>> ();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inbox_list);

        Bundle bundle = getIntent().getExtras();

        if(Global.jsonList!=null){
            for (int i = 0; i < Global.jsonList.size(); i++) {
                JsonModel model=Global.jsonList.get(i);
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                // adding each child node to HashMap key => value
                map.put("AD", model.getBankaAdi());
                map.put("ALIS", model.getEuroAlis());
                map.put("SATIS", model.getEuroSatis());


                if("0".equals(model.getEuroDurum()))
                    map.put("DURUM",String.valueOf(R.drawable.normal));
                else    if("1".equals(model.getEuroDurum()))
                    map.put("DURUM",String.valueOf(R.drawable.cikis));
                else
                    map.put("DURUM", String.valueOf(R.drawable.dusus));

                inboxList.add(map);


            }
            if(Global.jsonList!=null && Global.jsonList.size()>0){
            ListAdapter adapter = new SimpleAdapter(
                    EuroActivity.this, inboxList,
                    R.layout.inbox_list_item, new String[] { "AD", "ALIS", "SATIS","DURUM" },
                    new int[] { R.id.bankaadi, R.id.alis, R.id.satis,R.id.durum });
            // updating listview
            setListAdapter(adapter);}

    }}
}
