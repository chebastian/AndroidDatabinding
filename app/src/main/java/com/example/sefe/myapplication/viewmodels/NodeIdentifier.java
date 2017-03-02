package com.example.sefe.myapplication.viewmodels;

import com.example.sefe.myapplication.model.DetailsModel;

/**
 * Created by sefe on 3/2/2017.
 */
public class NodeIdentifier implements Comparable<Integer>{
    public final int Identifier;

    public NodeIdentifier(int id)
    {
        Identifier = id;
    }

    @Override
    public int compareTo(Integer i) {
        return Identifier - i;
    }
}
