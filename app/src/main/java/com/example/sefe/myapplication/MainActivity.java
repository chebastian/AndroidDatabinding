package com.example.sefe.myapplication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        firstFragment frag = new firstFragment();
        trans.replace(R.id.activity_main,frag).commit();
    }
}
