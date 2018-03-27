package com.example.zeynep.kutuphanedeyimm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    Button ogrencigirisi,kutuphanecigirisi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ogrencigirisi=(Button)findViewById(R.id.ogrencigirisi);
        kutuphanecigirisi=(Button)findViewById(R.id.kutuphanegiris);



       ogrencigirisi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent myintent= new Intent(MainActivity.this,OgrenciGirisi.class);
               startActivity(myintent);
           }
       });



    }



}
