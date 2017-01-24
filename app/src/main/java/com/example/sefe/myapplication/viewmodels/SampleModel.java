package com.example.sefe.myapplication.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Random;

/**
 * Created by sefe on 1/19/2017.
 */
public class SampleModel extends BaseObservable{

    public interface ClickedListener
    {
        public void clicked(SampleModel model);
    }

    public String Name;
    public int Age;
    public ClickedListener Listener = null;
    public ObservableField<Boolean> ChildrenVisible;

    public ObservableArrayList<String> things;


    public SampleModel(String name, int age)
    {
        Name = name;
        Age = age;
        things = new ObservableArrayList<>();
        things.add(name + "TEST");
        things.add(name + "TEST2");
        things.add("TEST3");
        things.add("TEST4");

        Random r = new Random();
        for(int i = 0; i < r.nextInt(100); i++)
            things.add("test" + i);
        ChildrenVisible = new ObservableField<>();
        ChildrenVisible.set(false);
    }

    @Override
    public String toString()
    {
        return new StringBuilder().append("Nmae: ").append((Name)).append("\n")
                .append("Age: ").append(Age).toString();

    }

    public void clicked(View v)
    {
        if(Listener != null)
            Listener.clicked(this);
        Log.d("C", "CLIECKED");
        ChildrenVisible.set(!ChildrenVisible.get());
    }
}
