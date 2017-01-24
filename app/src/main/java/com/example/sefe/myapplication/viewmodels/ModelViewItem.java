package com.example.sefe.myapplication.viewmodels;

/**
 * Created by sefe on 1/24/2017.
 */
public class ModelViewItem<ITEM,MV> {

    ITEM item;
    MV mv;
    public ModelViewItem(ITEM item, MV mv)
    {
        this.item = item;
        this.mv = mv;
    }
}
