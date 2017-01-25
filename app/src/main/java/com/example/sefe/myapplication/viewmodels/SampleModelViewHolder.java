package com.example.sefe.myapplication.viewmodels;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.sefe.myapplication.databinding.FragmentFirstBinding;
import com.example.sefe.myapplication.databinding.ModelItemBinding;

/**
 * Created by sefe on 1/20/2017.
 */

public class SampleModelViewHolder extends RecyclerViewItemHolder<SampleModel,ModelItemBinding>{

    //ModelItemBinding binding;
    public SampleModelViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(SampleModel model)
    {
        binding.setModel(model);
    }

}
