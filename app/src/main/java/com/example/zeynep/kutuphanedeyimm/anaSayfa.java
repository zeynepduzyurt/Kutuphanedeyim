package com.example.zeynep.kutuphanedeyimm;
// Kütüphaneler, Hesabım, En Yakın Kütüphane, Kitap Önerisi listesinin bulunduğu sayfa.
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class anaSayfa extends AppCompatActivity {
    Button kutuphaneler, hesap, burdayim, favoriekle, harita, kitapOnerisi;
  //  InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        kutuphaneler = (Button) findViewById(R.id.library);
        hesap = (Button) findViewById(R.id.button4);
        burdayim = (Button) findViewById(R.id.button2);
        harita = (Button) findViewById(R.id.haritalar);
        kitapOnerisi = (Button) findViewById(R.id.kitapBtn);

       /* mInterstitialAd = new InterstitialAd(this);        // Admob reklam eklentisi
        mInterstitialAd.setAdUnitId("ca-app-pub-9545523423977138/8630130577");
        reklamiYukle();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                reklamiYukle();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });

*/
        kutuphaneler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(anaSayfa.this, CustomListView.class);
                startActivity(myintent);
            }
        });

        hesap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(anaSayfa.this, hesabim.class));
            }
        });

        harita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(anaSayfa.this, MapsActivity.class));
            }
        });

        burdayim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent burdayim = new Intent(anaSayfa.this, CustomListView.class);
                startActivity(burdayim);
                Toast.makeText(getApplicationContext(), "Lütfen bulunduğunuz kütüphaneyi seçin.", Toast.LENGTH_SHORT).show();

            }
        });

        kitapOnerisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(anaSayfa.this, KitapOnerisi.class));
            }
        });


    }

  /*  private void reklamiYukle() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstitialAd.loadAd(adRequest);
    }
*/

}

