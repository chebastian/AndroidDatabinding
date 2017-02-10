package com.example.sefe.myapplication.viewmodels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sefe on 1/25/2017.
 * @Param T: Something something
 * @Param M: The model that...
 *
 */
public abstract class RecyclerBindingAdapter<M,T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

    private final List<M>  items;
    public RecyclerBindingAdapter(List<M> theItems)
    {
        super();
        items = theItems;
    }

//    @Override
//    public void onBindViewHolder(RecyclerViewItemHolder<M, T> holder, int position) {
//        M model = items.get(position);
//        holder.bind(model);
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
}
