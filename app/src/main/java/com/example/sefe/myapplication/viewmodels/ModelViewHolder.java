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

public class ModelViewHolder extends RecyclerView.ViewHolder {

    ModelItemBinding binding;
    public ModelViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(SampleModel model)
    {
        binding.setModel(model);
    }

}
