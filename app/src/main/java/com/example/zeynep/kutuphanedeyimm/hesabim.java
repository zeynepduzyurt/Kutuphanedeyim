package com.example.zeynep.kutuphanedeyimm;
// Hesabım sayfası, çıkış yapma ve kullanıcı silme işlemleri

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class hesabim extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button kullaniciSil, cikisYap;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesabim);

        kullaniciSil = (Button) findViewById(R.id.kullaniciSil);
        textView = (TextView) findViewById(R.id.text);
        cikisYap = (Button) findViewById(R.id.cikis_yap);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    startActivity(new Intent(hesabim.this, OgrenciGirisi.class));
                    finish();
                }
            }
        };

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        textView.setText("Merhaba! \n  " + user.getDisplayName());

        kullaniciSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(hesabim.this, "Hesabın silindi, yeni bir hesap oluştur.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(hesabim.this, UyeOl.class));
                                finish();
                            } else {
                                Toast.makeText(hesabim.this, "Hesap silinemedi!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(hesabim.this, OgrenciGirisi.class));
                finish();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}
