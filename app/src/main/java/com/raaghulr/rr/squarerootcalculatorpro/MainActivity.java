package com.raaghulr.rr.squarerootcalculatorpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.banner.RevMobBanner;

public class MainActivity extends AppCompatActivity {
    RevMob revmob;
    RevMobBanner banner;
    EditText et_getvalue;
    TextView tv_resultvalue;
    double var_store,result_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intialise
        et_getvalue = (EditText)findViewById(R.id.et_getvalue);
        tv_resultvalue = (TextView)findViewById(R.id.tv_resultvalue);
        //
        startRevMobSession();
    }

    public void squareCalculator (View view)
    {

        if (et_getvalue.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Enter a Number!!!", Toast.LENGTH_SHORT).show();
            var_store = 0 ;
        }
        else
            var_store = new Double(String.valueOf(et_getvalue.getText()));
        result_store = Math.sqrt(var_store);
        tv_resultvalue.setText((new String(String.valueOf(result_store))));
        Toast.makeText(this, new String("Square Root Value For \nNumber : "+var_store+"  is "+String.valueOf(result_store)), Toast.LENGTH_SHORT).show();
    }

    public void clearValue(View view)
    {
        et_getvalue.setText("");
        tv_resultvalue.setText("Answer");
        Toast.makeText(this, "Value Cleared!!!", Toast.LENGTH_SHORT).show();
    }

    public void startRevMobSession() {
        //RevMob's Start Session method:
        revmob = RevMob.startWithListener(MainActivity.this, new RevMobAdsListener() {
            @Override
            public void onRevMobSessionStarted() {
                loadBanner(); // Cache the banner once the session is started
                Log.i("RevMob","Session Started");
            }
            @Override
            public void onRevMobSessionNotStarted(String message) {
                //If the session Fails to start, no ads can be displayed.
                Log.i("RevMob","Session Failed to Start");
            }
        }, "58e0873530976e7e39e0349f");
    }

    public void loadBanner(){
        banner = revmob.preLoadBanner(this, new RevMobAdsListener(){
            @Override
            public void onRevMobAdReceived() {
                showBanner();
                Log.i("RevMob","Banner Ready to be Displayed"); //At this point, the banner is ready to be displayed.
            }
            @Override
            public void onRevMobAdNotReceived(String message) {
                Log.i("RevMob","Banner Not Failed to Load");
            }
            @Override
            public void onRevMobAdDisplayed() {
                Log.i("RevMob","Banner Displayed");
            }
        });
    }

    public void showBanner(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewGroup view = (ViewGroup) findViewById(R.id.bannerLayout);
                view.addView(banner);
                banner.show(); //This method must be called in order to display the ad.
            }
        });
    }
}
