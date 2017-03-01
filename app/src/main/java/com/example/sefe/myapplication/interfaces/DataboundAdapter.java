package com.example.sefe.myapplication.interfaces;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sefe on 3/1/2017.
 */
public class DataboundAdapter<ItemViewModel extends Object> extends ArrayAdapter<ItemViewModel>{

    private static final String TAG = "DB_ADAPTER";
    protected static LayoutInflater inflater = null;
    protected  @LayoutRes int _layoutId;
    protected ArrayList<ItemViewModel> _vms;

    public DataboundAdapter(Context context, List<ItemViewModel> vm, @LayoutRes int resource) {
        super(context, resource);
        _layoutId = resource;
        _vms = new ArrayList<>();
        _vms.addAll(vm);
//        Log.d(TAG,"Created");
    }

    @Override
    public int getCount()
    {
        return _vms.size();
    }

    @Override
    public View getView(int position, View converterView, ViewGroup parent)
    {
//        Log.d(TAG,"getView called");
        if(inflater == null)
        {
            inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        ViewDataBinding binding = DataBindingUtil.inflate(inflater,_layoutId, parent,false);
        binding.setVariable(BR.viewmodel,_vms.get(position));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
