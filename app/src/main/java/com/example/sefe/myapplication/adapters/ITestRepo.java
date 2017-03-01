package com.example.sefe.myapplication.adapters;

import com.example.sefe.myapplication.viewmodels.SampleModel;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by sefe on 2/25/2017.
 */
public interface ITestRepo {
    void GetData(Consumer<List<SampleModel>> consumer);
    void GetDataAtPosition(Consumer<SampleModel> consumer,int index);
    int DataCount();

}
