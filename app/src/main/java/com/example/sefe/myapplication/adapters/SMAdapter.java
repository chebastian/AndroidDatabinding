package com.example.sefe.myapplication.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sefe.myapplication.IXryFileRepository;
import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.databinding.FragmentFirstBinding;
import com.example.sefe.myapplication.databinding.ModelItemBinding;
import com.example.sefe.myapplication.viewmodels.FirstVM;
import com.example.sefe.myapplication.viewmodels.SampleModel;
import com.example.sefe.myapplication.viewmodels.SampleModelViewHolder;

import io.reactivex.functions.Consumer;

/**
 * Created by sefe on 2/25/2017.
 */

public class SMAdapter extends RecyclerView.Adapter<SMAdapter.ViewHodler> {

    private final ITestRepo _repo;

    public SMAdapter(ITestRepo repo) {
        super();
        _repo = repo;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View status = inflater.inflate(R.layout.model_item, parent, false);
        return new ViewHodler(status);
    }

    @Override
    public void onBindViewHolder(final ViewHodler holder, int position) {
        Log.d("dd", "pos: " + position);
        _repo.GetDataAtPosition(new Consumer<SampleModel>() {
            @Override
            public void accept(SampleModel sampleModel) throws Exception {
                holder.bind(sampleModel);
            }
        }, position);

        holder.bind(new SampleModel("LOADING",1) );
    }

    @Override
    public int getItemCount() {
        return _repo.DataCount();
    }

    public static class ViewHodler extends RecyclerView.ViewHolder {

        ModelItemBinding binding;

        public ViewHodler(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind(SampleModel model) {
            binding.setModel(model);
        }
    }
}
