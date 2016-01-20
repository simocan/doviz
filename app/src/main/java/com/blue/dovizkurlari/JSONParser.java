package com.blue.dovizkurlari;

import android.os.StrictMode;

import com.blue.model.JsonModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONParser {

    static InputStream iStream = null;
    static JSONArray jarray = null;
    static String json = "";
    ArrayList<JsonModel> jsonList = new ArrayList<JsonModel>();


    private static final String JSON_URL = "http://blueyazilim.com/doviz/index.php";

    // constructor
    public JSONParser() {

    }

    public List getJSONFromUrl() throws MalformedURLException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(JSON_URL);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // Parse String to JSON object

        try {

            jarray = new JSONArray(builder.toString());

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject object = jarray.getJSONObject(i);

                JsonModel model = new JsonModel();
                model.setId(object.getString("item_type_id"));
                model.setBankaAdi(object.getString("banka_adi"));
                model.setDolarSatis(object.getString("dolar_satis"));
                model.setDolarAlis(object.getString("dolar_alis"));
                model.setDolarDurum(object.getString("dolar_durum"));

                model.setEuroSatis(object.getString("euro_satis"));
                model.setEuroAlis(object.getString("euro_alis"));
                model.setEuroDurum(object.getString("euro_durum"));


                model.setAltinSatis(object.getString("altin_satis"));
                model.setAltinAlis(object.getString("altin_alis"));
                model.setAltinDurum(object.getString("altin_durum"));

                jsonList.add(model);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } // return JSON Object return jarray;

        return jsonList;
    }
}