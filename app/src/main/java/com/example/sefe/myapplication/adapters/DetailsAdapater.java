package com.example.sefe.myapplication.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.interfaces.DataboundAdapter;
import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.model.DetailsModel;

import java.util.List;
import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by sefe on 3/1/2017.
 */
public class DetailsAdapater extends DataboundAdapter<DetailsModel>{

    private IDetailsRepository _repo;
    public DetailsAdapater(Context ctx, List<DetailsModel> items, IDetailsRepository repo) {
        super(ctx,items, R.layout.details_item_layout);
        _repo = repo;
    }

}
