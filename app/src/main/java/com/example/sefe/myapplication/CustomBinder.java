package com.example.sefe.myapplication;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sefe.myapplication.databinding.ModelItemBinding;
import com.example.sefe.myapplication.viewmodels.SampleModel;

import java.util.ArrayList;

/**
 * Created by sefe on 1/20/2017.
 */

public class CustomBinder {

    @BindingAdapter("bind:isVisible")
    public static void setVisible(View v, boolean b)
    {
    }

    @BindingAdapter("bind:litem")
    public static void setListItems(LinearLayout l, SampleModel m)
    {
        LayoutInflater infl = (LayoutInflater) l.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(l.getChildCount() <= 0){
            for(int i = 0; i < m.things.size(); i++){
                View v = infl.inflate(R.layout.item_string,null);
                //View v = infl.inflate(R.layout.item_string,(ViewGroup) l.getParent().getParent());
                ((TextView)v.findViewById(R.id.txtItem)).setText(m.Name + " Child" + m.things.get(i));
                l.addView(v);
            }
        }

//        ModelItemBinding binding = DataBindingUtil.inflate(infl,R.layout.model_item,l.getParent(),false);
//        binding.setModel(list);
    }

}
