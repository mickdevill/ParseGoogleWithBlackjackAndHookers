package com.mickdevil.parsegooglewithblackjackandhookers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Browser;
import android.service.media.MediaBrowserService;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button parse;

    private Document doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parse = findViewById(R.id.parse);
        Thread thread = new Thread(new secT());


        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!thread.isAlive()) {
                    thread.start();
                    Log.d(TAG, "onCreate: " + "it start the sec thread!!!");
                }
            }
        });

    }


    private void getWeb() {

        String webAdress = "https://api.foursquare.com/v2/venues/search?ll=47.2122%2C-1.5450&radius=5000&categoryId" +
                "=4d4b7105d754a06374d81259&client_id=MDW1CQIF4AUNLX2VXX2FJ1CA4CZIWQ3ACUQTCK0JNQ2MXVRO&client_secret=YI1SJ" +
                "4Q4CKJZV3NK0YPGCCLIYZTRLCNTW3MDBHPBRJXMUUCL&v=20200101";
        String line = "";
        String data = "";
        JSONObject Johny = null;

        try {
            URL url = new URL(webAdress);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            //  urlConnection.setRequestMethod("GET");
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (line != null) {

                line = bufferedReader.readLine();
                data += line;
            }

            Johny = new JSONObject(data);

              Log.d(TAG, "getPlaces: " + data);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public class secT implements Runnable {

        @Override
        public void run() {
            getWeb();
        }
    }

















}