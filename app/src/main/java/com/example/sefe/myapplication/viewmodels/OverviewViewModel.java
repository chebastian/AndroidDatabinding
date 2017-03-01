package com.example.sefe.myapplication.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by sefe on 2/28/2017.
 */

public class OverviewViewModel {

    private final IOnAlternativeClicked _onAlternativeClicked;

    public interface IOnAlternativeClicked
    {
        void OnAlternativeClicked(int alternative);
    }

    public ObservableField<String> Name;
    private ObservableArrayList<String> Alternatives;

    public OverviewViewModel(IOnAlternativeClicked clickedListener)
    {
        _onAlternativeClicked = clickedListener;
        Name = new ObservableField<>("Overview Name");
        Alternatives = new ObservableArrayList<>();
        Alternatives.add("First");
        Alternatives.add("Second");
        Alternatives.add("3rd");
        Alternatives.add("4yh");
    }

    public ObservableArrayList<String> Alternatives()
    {
        return Alternatives;
    }

    public AdapterView.OnItemClickListener AlternativeClickListener()
    {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("OV_VM", "clicked " + position);
                _onAlternativeClicked.OnAlternativeClicked(position);
            }
        };
    }
}
