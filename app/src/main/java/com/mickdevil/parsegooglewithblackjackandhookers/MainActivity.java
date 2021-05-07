package com.mickdevil.parsegooglewithblackjackandhookers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Browser;
import android.service.media.MediaBrowserService;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

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
                if (!thread.isAlive()){
                    thread.start();
                    Log.d(TAG, "onCreate: " + "it start the sec thread!!!");
                }
            }
        });

    }


private void getWeb(){
    try {



        // doc = Jsoup.connect("https://www.google.fr/maps/search/kebab/@47.4793553,-0.5454856,13z/data=!4m2!2m1!6e5").get();
        doc = Jsoup.connect("https://www.google.fr/maps/search/kebab/@47.4793553,-0.5454856,13z/data=!4m2!2m1!6e5").get();


        doc = Jsoup.connect("https://www.google.ru/maps/place/Ashiq+Muhammad/data=!4m5!3m4!1s0x480878ead1b59d01:0x872d" +
                "fd9e9c1f2bd7!8m2!3d47.4677682!4d-0.5505496?authuser=0&amp;hl=ru&amp;rclk=1\" class=\"place-result-container-place" +
                "-link\" jsan=\"7.place-result-container-place-link,0.aria-label,8.href,0.jsaction").get();

        Log.d(TAG, "getWeb: " + doc.text());

      //  getCookie()


    } catch (IOException e) {
        e.printStackTrace();
    }


}



    public String getCookie(String siteName,String CookieName){
        String CookieValue = null;

        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(siteName);
        if(cookies != null){
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(CookieName)){
                    String[] temp1=ar1.split("=");
                    CookieValue = temp1[1];
                }
            }
        }
        return CookieValue;
    }







public class secT implements Runnable{

    @Override
    public void run() {
        getWeb();
    }
}






}