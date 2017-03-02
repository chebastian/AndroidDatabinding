package com.example.sefe.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.databinding.tool.Binding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sefe on 2/28/2017.
 */
public class DatabindingFragment<VM extends Object> extends Fragment{

    protected static final String PARAM_LAYOUT_ID = "layout_id";
    protected static final String PARAM_VM = "param_vm";

    protected @LayoutRes  int _layoutId;
    protected ObservableField<VM> _viewModel;

    public DatabindingFragment()
    {
        super();
    }
    //ObservableField<ViewDataBinding> binding;
    ViewDataBinding binding;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _layoutId = getArguments().getInt(PARAM_LAYOUT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, _layoutId,container,false);
        binding.setVariable(BR.viewmodel,_viewModel.get());
        return binding.getRoot();
    }
}
