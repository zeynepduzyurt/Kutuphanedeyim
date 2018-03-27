package com.example.zeynep.kutuphanedeyimm;
//ilk 11 kütüphane için kişi sayısını veriyor.



import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.preference.RingtonePreference;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;


public class SecondActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView library;
    TextView textView, textView1, kisi;
    private Button burdayim, burdadegilim, butonfav,kisigor;
    RatingBar ratingBar;
    FirebaseDatabase database2;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference ref = database.getReference();
    final DatabaseReference ref1 = database.getReference();
    final DatabaseReference ref2 = database.getReference();
   final DatabaseReference ref3 = database.getReference();
    final DatabaseReference ref4 = database.getReference();
    final DatabaseReference ref5 = database.getReference();
     final DatabaseReference ref6= database.getReference();
    final DatabaseReference ref7 = database.getReference();
   final DatabaseReference ref8 = database.getReference();
    final DatabaseReference ref9= database.getReference();
    final DatabaseReference ref10 = database.getReference();
  /*   final DatabaseReference ref11 = database.getReference();
    final DatabaseReference ref12 = database.getReference();
    final DatabaseReference ref13 = database.getReference();
    final DatabaseReference ref14 = database.getReference();
    final DatabaseReference ref15 = database.getReference();
    final DatabaseReference ref16 = database.getReference();
    final DatabaseReference ref17= database.getReference();
    final DatabaseReference ref18 = database.getReference();
    final DatabaseReference ref19 = database.getReference();*/


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        library = (ImageView) findViewById(R.id.imageView2);
        textView = (TextView) findViewById(R.id.textview1);
        textView1 = (TextView) findViewById(R.id.textView);
        burdayim = (Button) findViewById(R.id.button3);
        burdadegilim = (Button) findViewById(R.id.button5);
        kisi = (TextView) findViewById(R.id.kisisayisi);
        butonfav = (Button) findViewById(R.id.buttonfav);
        kisigor = (Button) findViewById(R.id.kisigorr);


        database2 = FirebaseDatabase.getInstance();

        final DatabaseReference upvotesRef = ref.child("Adile Sultan");
        final DatabaseReference upvotesRef1= ref1.child("Ahmet Hamdi Tanpınar");
        final DatabaseReference upvotesRef2= ref2.child("Ahmet Kabaklı");
       final DatabaseReference upvotesRef3 = ref3.child("Ataturk Kitaplığı");
        final DatabaseReference upvotesRef4= ref4.child("Beyazıt Devlet");
        final DatabaseReference upvotesRef5= ref5.child("İSAM");
       final DatabaseReference upvotesRef6= ref6.child("Modern");
        final DatabaseReference upvotesRef7= ref7.child("Orhan Kemal İl");
    final DatabaseReference upvotesRef8= ref8.child("İZU Merkez");
        final DatabaseReference upvotesRef9= ref9.child("Kadın Eserleri");
        final DatabaseReference upvotesRef10= ref10.child("Metin And");
         /*     final DatabaseReference upvotesRef11= ref11.child("Osman Akfırat");
        final DatabaseReference upvotesRef12 = ref12.child("Rasim Özdenören");
        final DatabaseReference upvotesRef13= ref13.child("Salt Galata");
        final DatabaseReference upvotesRef14= ref14.child("Selimiye Halk K.");
        final DatabaseReference upvotesRef15 = ref15.child("Silivri Halk K.");
        final DatabaseReference upvotesRef16= ref16.child("ŞSVM");
        final DatabaseReference upvotesRef17= ref17.child("Şemsipaşa");
        final DatabaseReference upvotesRef18 = ref18.child("Tarih K.");
        final DatabaseReference upvotesRef19= ref19.child("Turabibaba");
*/




        // Query recentPostsQuery = ref.child("upvotes").limitToFirst(5);


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView1.setText("Derece " + rating);
            }
        });


        butonfav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(SecondActivity.this,
                        "Favorilere eklendi.",
                        Toast.LENGTH_SHORT).show();

            }

        });

        Bundle bundle = getIntent().getExtras();
        burdadegilim.setClickable(true);
        burdayim.setClickable(true);

        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("Kütüphane Adı"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Adile Sultan Halk Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.adilesultan));
                textView.setText("Vakıf Mektep Sok. No: 13 34220 \n Fatih/İstanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef.runTransaction(new Transaction.Handler() {
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }

                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }


                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref.child("Adile Sultan").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/150");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            }else if (mToolbar.getTitle().toString().equalsIgnoreCase("Ahmet Hamdi Tanpınar Edebiyat Müze Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.ahmethamdi));
                textView.setText("Alemdar Mah., Alemdar Cad. Alay Köşkü, 34122 \n Fatih/Istanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef1.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        upvotesRef1.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref1.child("Ahmet Hamdi Tanpınar").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/170");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });


            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Ahmet Kabaklı Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.ahmetkabakli));
                textView.setText("Mimar Sinan Mah. Akşemseddin Cad. No: 52 34091 \n Fatih/İSTANBUL\n Kitap Sayısı: 30.000");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef2.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef2.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref2.child("Ahmet Kabaklı").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });

            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Atatürk Kitaplığı")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.ataturkitap));
                textView.setText("Gümüşsuyu Mahallesi, Miralay Şefik Bey Sok. No:6, 34437 \n Beyoğlu/Istanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef3.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef3.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref3.child("Ataturk Kitaplığı").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Beyazıt Devlet Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.beyazitdevlet));
                textView.setText("Beyazıt Mh., Çadırcılar Cd. No:4, 34126 \n Fatih/Istanbul \n" +"Kitap Sayısı:1000" );
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef4.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef4.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref4.child("Beyazıt Devlet").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İslam Arastırmaları Merkezi (İSAM) Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.isam));
                textView.setText("Altunizade Mahallesi, İcadiye Bağlarbaşı Cd. No:40, 34672 \n Üsküdar/Istanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef5.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef5.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref5.child("İSAM").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Modern Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.modernkutup));
                textView.setText("Kılıçali Paşa Mahallesi, Meclis-i Mebusan Cad. No:4, 34433 \n Beyoğlu/İstanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef6.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef6.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref6.child("Modern").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Orhan Kemal İl Halk Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.orhankemal));
                textView.setText("Mimar Kemalettin Mahallesi, Ordu Cd. No:33, 34130 \n Fatih/Istanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef7.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef7.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref7.child("Orhan Kemal İl").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Sabahattin Zaim Üniversitesi Merkez Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.izukutup));
                textView.setText("Halkalı Mahallesi, Halkalı Caddesi, Halkalı Kampüsü, 34303 \n Küçükçekmece/İstanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef8.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef8.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref8.child("İZU Merkez").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Kadın Eserleri Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.kadineser));
                textView.setText("Balat Mahallesi, Kadir Has Kavşağı No:8, 34083 \n Fatih/Istanbul");
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef9.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef9.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref9.child("Kadın Eserleri").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Metin And Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.metinand));
                textView.setText("Gültepe Mah. Bağlar Cad. No:61 \n Sefaköy/İstanbul\n Kitap Sayısı: 8.000");  burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef10.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(final MutableData mutableData) {
                                final Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(true);
                                burdayim.setClickable(false);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue + 1);
                                }

                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }

                        });


                    }
                });


                burdadegilim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef10.runTransaction(new Transaction.Handler() {
                            @Override
                            public Transaction.Result doTransaction(MutableData mutableData) {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                burdadegilim.setClickable(false);
                                burdayim.setClickable(true);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }

                                return Transaction.success(mutableData);


                            }

                            @Override
                            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                                System.out.println("Transaction completed");
                            }
                        });
                    }
                });
                kisigor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref10.child("Metin And").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value=dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString()+"/180");
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Osman Akfırat Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.osmanakfir));
                textView.setText("Necmettin Erbakan Kültür Merkezi Gümüşsuyu Mah. Kelle İbrahim Cad. No:45 \n Beykoz/İSTANBUL\n\n Kitap Sayısı: 14.000");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Rasim Özdenören Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rasimozden));
                textView.setText("Başakşehir Kültür Merkezi Başakşehir Mah. Süleyman Çelebi Cad. Ilgaz Sok. No:4-A \n Başakşehir/İstanbul\n\n Kitap Sayısı: 15.000");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("SALT Galata")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.saltgalata));
                textView.setText("Arap Cami Mahallesi, No:, Bankalar Cd. No:11, 34420 \n Beyoğlu/Istanbul");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Selimiye Halk Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.selimiye));
                textView.setText("Selimiye Kışla Cad. No:54 \n Üsküdar/İstanbul\n\n Kitap Sayısı : 12930");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Silivri Halk Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.silivri));
                textView.setText("Piri Mehmet Paşa Mah., Şerif Sok 23/B, 34570 \n Silivri/Istanbul");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Şehit Savcı Mehmet Selim Kiraz Kültür Merkezi Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.bagcilark));
                textView.setText("Sancaktepe Mahallesi, 3/6C Sk. No:3, 34200 \n Bağcılar/Istanbul");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Şemsipaşa Halk Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.semsipasa));
                textView.setText("Mimar Sinan Mahallesi, Harem Yolu No:6, 34664 \n Üsküdar/Istanbul");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Tarih, Edebiyat ve Sanat Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.tarihedebiyat));
                textView.setText("Caferağa Mahallesi,, Rıhtım Caddesi, No:2 D:3, 34710 \n Kadıköy/Istanbul");
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Turabibaba Kütüphanesi")) {
                library.setImageDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.turabibaba));
                textView.setText("Camii Kebir Mahallesi, Turabibaba Caddesi, Matbaa Arkası Sokak No:2, 34440 \n Beyoğlu/Istanbul");
            }

        }


    }
}







