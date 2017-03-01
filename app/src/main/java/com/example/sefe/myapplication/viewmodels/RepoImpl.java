package com.example.sefe.myapplication.viewmodels;

import com.example.sefe.myapplication.adapters.ITestRepo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sefe on 2/25/2017.
 */
public class RepoImpl implements ITestRepo{
    ArrayList<SampleModel> items;

    public RepoImpl()
    {
        items = new ArrayList<>();
        items.add(new SampleModel("First",1));
        items.add(new SampleModel("Second",12));
        items.add(new SampleModel("third",13));
    }
    @Override
    public void GetData(final Consumer<List<SampleModel>> consumer) {
        Maybe.create(new MaybeOnSubscribe<List<SampleModel>>() {
            @Override
            public void subscribe(MaybeEmitter<List<SampleModel>> e) throws Exception {
                e.onSuccess(items);
            }
        }).observeOn(Schedulers.io()).subscribe(consumer);
    }

    @Override
    public void GetDataAtPosition(final Consumer<SampleModel> consumer, final int index) {
        try {
            Maybe.create(new MaybeOnSubscribe<SampleModel>() {
                @Override
                public void subscribe(MaybeEmitter<SampleModel> e) throws Exception {
                    consumer.accept(items.get(index));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int DataCount() {
        return items.size();
    }
}
