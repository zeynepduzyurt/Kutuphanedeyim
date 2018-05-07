package com.example.zeynep.kutuphanedeyimm;
//Kütüphane listeleri, kütüphanede bulunan kişi sayısı durumu


import android.app.Activity;
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


public class SecondActivity extends Activity {
    public static final String EXTRA_LIBRARYID = "libraryID";
    Toolbar mToolbar;
    ImageView library;
    TextView textView, textView1, kisi;
    private Button burdayim, burdadegilim, butonfav, kisigor;
    RatingBar ratingBar;
    FirebaseDatabase database2;


    final FirebaseDatabase database = FirebaseDatabase.getInstance(); // Firebase'de her bir kütüphane için veri oluşturma
    final DatabaseReference ref = database.getReference();
    final DatabaseReference ref1 = database.getReference();
    final DatabaseReference ref2 = database.getReference();
    final DatabaseReference ref3 = database.getReference();
    final DatabaseReference ref4 = database.getReference();
    final DatabaseReference ref5 = database.getReference();
    final DatabaseReference ref6 = database.getReference();
    final DatabaseReference ref7 = database.getReference();
    final DatabaseReference ref8 = database.getReference();
    final DatabaseReference ref9 = database.getReference();
    final DatabaseReference ref10 = database.getReference();
    final DatabaseReference ref11 = database.getReference();
    final DatabaseReference ref12 = database.getReference();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int libraryID = (Integer) getIntent().getExtras().get(EXTRA_LIBRARYID);
        LibraryListClass libraryListClass = LibraryListClass.libraryList[libraryID];

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        mToolbar.setTitle(libraryListClass.getLibraryName());

        TextView textView = (TextView) findViewById(R.id.textview1);  //adres
        textView.setText(libraryListClass.getDescription());

        ImageView library = (ImageView) findViewById(R.id.imageView2);
        library.setImageResource(libraryListClass.getLibraryImage());
        library.setContentDescription(libraryListClass.getLibraryName());

        textView1 = (TextView) findViewById(R.id.textView);
        burdayim = (Button) findViewById(R.id.button3);
        burdadegilim = (Button) findViewById(R.id.button5);
        kisi = (TextView) findViewById(R.id.kisisayisi);
        butonfav = (Button) findViewById(R.id.buttonfav);
        kisigor = (Button) findViewById(R.id.kisigorr);

        database2 = FirebaseDatabase.getInstance();

        final DatabaseReference upvotesRef = ref.child("Adile Sultan");  // Firebase'de kişi bildirimi yapılırken hangi kütüphaneye ait olduğunu belirleme işlemi
        final DatabaseReference upvotesRef1 = ref1.child("Ahmet Hamdi Tanpınar");
        final DatabaseReference upvotesRef2 = ref2.child("Ahmet Kabaklı");
        final DatabaseReference upvotesRef3 = ref3.child("Ataturk Kitaplığı");
        final DatabaseReference upvotesRef4 = ref4.child("Beyazıt Devlet");
        final DatabaseReference upvotesRef5 = ref5.child("İSAM");
        final DatabaseReference upvotesRef6 = ref6.child("Modern");
        final DatabaseReference upvotesRef7 = ref7.child("Orhan Kemal İl");
        final DatabaseReference upvotesRef8 = ref8.child("İZU Merkez");
        final DatabaseReference upvotesRef9 = ref9.child("Kadın Eserleri");
        final DatabaseReference upvotesRef10 = ref10.child("Metin And");
        final DatabaseReference upvotesRef11 = ref11.child("Osman Akfırat");
        final DatabaseReference upvotesRef12 = ref12.child("Rasim Özdenören");


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
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Adile Sultan Halk Kütüphanesi")) {
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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/150");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Ahmet Hamdi Tanpınar Edebiyat Müze Kütüphanesi")) {
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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/170");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });


            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Ahmet Kabaklı Kütüphanesi")) {
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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });

            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Atatürk Kitaplığı")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Beyazıt Devlet Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            }

            else if (mToolbar.getTitle().toString().equalsIgnoreCase("İSAM Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            }

             else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Modern Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Orhan Kemal İl Halk Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("İstanbul Sabahattin Zaim Üniversitesi Merkez Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Kadın Eserleri Kütüphanesi")) {

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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Metin And Kütüphanesi")) {
                burdayim.setOnClickListener(new View.OnClickListener() {
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
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });
            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Osman Akfırat Kütüphanesi")) {
                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef11.runTransaction(new Transaction.Handler() {
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

                        upvotesRef11.runTransaction(new Transaction.Handler() {
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
                        ref11.child("Osman Akfırat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });


            } else if (mToolbar.getTitle().toString().equalsIgnoreCase("Rasim Özdenören Kütüphanesi")) {

                burdayim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        upvotesRef12.runTransaction(new Transaction.Handler() {
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

                        upvotesRef12.runTransaction(new Transaction.Handler() {
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
                        ref12.child("Rasim Özdenören").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final Integer value = dataSnapshot.getValue(Integer.class);
                                kisi.setText(value.toString() + "/180");
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                });


            }


        }
    }


}






