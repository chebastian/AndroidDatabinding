package com.example.sefe.myapplication.viewmodels;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sefe.myapplication.R;
import com.example.sefe.myapplication.databinding.ModelItemBinding;

import java.util.List;

/**
 * Created by sefe on 1/20/2017.
 */
public class SampleModelAdapter extends RecyclerBindingAdapter<SampleModel,SampleModelViewHolder> implements SampleModel.ClickedListener {

    private final List<SampleModel> items;
    SampleModel.ClickedListener Listener;
    ViewGroup _parent;

    public SampleModelAdapter(List<SampleModel> theItems, SampleModel.ClickedListener clicked)
    {
        super(theItems);
        items = theItems;
        Listener = clicked;
    }

    @Override
    public SampleModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View status = inflater.inflate(R.layout.model_item,parent,false);
        Log.d("Adapter", "Creating");
        _parent = parent;
        return new SampleModelViewHolder(status);
    }

    @Override
    public void onBindViewHolder(SampleModelViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void clicked(SampleModel model) {
        TransitionManager.beginDelayedTransition(_parent);
    }
}
