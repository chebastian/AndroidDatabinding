package com.example.sefe.myapplication.model;

import android.databinding.ObservableField;

/**
 * Created by sefe on 2/28/2017.
 */
public class DetailsModel {
    public ObservableField<String> Name;

    public DetailsModel(String name)
    {
        Name = new ObservableField<>(name);
    }
}
