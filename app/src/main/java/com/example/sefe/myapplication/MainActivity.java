package com.example.sefe.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.sefe.myapplication.fragment.OverViewFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
//        firstFragment frag = new firstFragment();
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        Fragment theFrag = new OverViewFragment();
        trans.replace(R.id.activity_main,theFrag).commit();
    }
}
