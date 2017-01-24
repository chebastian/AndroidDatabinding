package com.example.sefe.myapplication.viewmodels;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sefe.myapplication.R;

import java.util.List;

/**
 * Created by sefe on 1/20/2017.
 */
public class SampleModelAdapter extends RecyclerView.Adapter<ModelViewHolder> implements SampleModel.ClickedListener {

    private final List<SampleModel> items;
    SampleModel.ClickedListener Listener;
    ViewGroup _parent;

    public SampleModelAdapter(List<SampleModel> theItems, SampleModel.ClickedListener clicked)
    {
        items = theItems;
        Listener = clicked;
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View status = inflater.inflate(R.layout.model_item,parent,false);
        _parent = parent;
        return new ModelViewHolder(status);
    }

    @Override
    public void onBindViewHolder(ModelViewHolder holder, int position) {
        SampleModel model = items.get(position);
        model.Listener = Listener;
        model.Listener = this;
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void clicked(SampleModel model) {
        TransitionManager.beginDelayedTransition(_parent);
    }
}
