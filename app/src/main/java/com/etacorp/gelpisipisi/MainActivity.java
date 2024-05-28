package com.etacorp.gelpisipisi;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    ImageView img;
    Button calButon;
    MediaPlayer ses;

    boolean calıyor = false;
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        img = findViewById(R.id.imageView2);
        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4818067817392821/3094854472");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });



        calButon = findViewById(R.id.cal);
        ses = MediaPlayer.create(this, R.raw.kedi);


        calButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!calıyor)
                {
                    a++;
                    calıyor = true;
                    ses.setLooping(true);
                    ses.start();
                    calButon.setText("Durdurmak İçin Basın");
                    img.setImageResource(R.drawable.kedi3);
                }
                else
                {
                    calıyor =false;
                    ses.pause();
                    calButon.setText("Kedinizi Çağırmak İçin Basın");
                    img.setImageResource(R.drawable.kedi2);
                }


                if (a > 4 && mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    a = 0;
                }

            }
        });


    }
}