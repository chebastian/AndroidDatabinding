package com.example.sefe.myapplication.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.databinding.InspectionFragmentLayoutBinding;
import com.example.sefe.myapplication.databinding.OverviewLayoutBinding;
import com.example.sefe.myapplication.model.DetailsRepoImpl;
import com.example.sefe.myapplication.viewmodels.OverviewViewModel;

/**
 * Created by sefe on 2/28/2017.
 */
public class OverViewFragment extends Fragment implements OverviewViewModel.IOnAlternativeClicked {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        OverviewLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.overview_layout,container,false);
        binding.setViewmodel(new OverviewViewModel(this));
        return binding.getRoot();
    }

    @Override
    public void OnAlternativeClicked(int alternative) {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        Fragment theFrag = InspectionViewFragment.newInstance(alternative,new DetailsRepoImpl());
        trans.addToBackStack(getTag());
        trans.replace(R.id.activity_main,theFrag).commit();
    }
}
