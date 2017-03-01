package com.example.sefe.myapplication.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.example.sefe.myapplication.DatabindingFragment;
import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.viewmodels.InspectionViewModel;

/**
 * Created by sefe on 2/28/2017.
 */
public class InspectionViewFragment extends DatabindingFragment<InspectionViewModel> {

    protected static final String PARAM_ALTERNATIVE_ID = "param_alt";
    private IDetailsRepository repository;

    public static InspectionViewFragment newInstance(int alternative, IDetailsRepository repo) {
        Bundle args = new Bundle();
        args.putInt(PARAM_LAYOUT_ID, R.layout.inspection_fragment_layout);
        args.putInt(PARAM_ALTERNATIVE_ID, alternative);
        InspectionViewFragment frag = new InspectionViewFragment();
        frag.setRepository(repo);
        frag.setArguments(args);
        return frag;
    }

    public InspectionViewFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstance)
    {
        _viewModel = new InspectionViewModel(getArguments().getInt(PARAM_ALTERNATIVE_ID),repository);
        super.onCreate(savedInstance);
    }

    public void setRepository(IDetailsRepository repository) {
        this.repository = repository;
    }
}
