package com.example.sefe.myapplication.viewmodels;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sefe on 1/24/2017.
 */
public abstract class RecyclerViewItemHolder<M,T> extends RecyclerView.ViewHolder{

   T binding;
   public RecyclerViewItemHolder(View itemView) {
      super(itemView);
   }
   public abstract void bind(M model);
};
