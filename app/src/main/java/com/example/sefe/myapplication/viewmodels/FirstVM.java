package com.example.sefe.myapplication.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sefe.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sefe on 1/18/2017.
 */

public class FirstVM extends BaseObservable implements SampleModel.ClickedListener {
    public final ObservableField<Integer> SomeNum;
    public final ObservableField<String> Name;
    public ObservableArrayList<SampleModel> ListOfThings;
    //    public ArrayAdapter<String> Adapter = null;
    public SampleModelAdapter Adapter;

    @BindingAdapter({"bind:listItems", "bind:viewmodel"})
    public static void ListOfThings(RecyclerView v, ObservableArrayList<SampleModel> list, FirstVM vm) {
//        if(vm.Adapter == null)
        {
            vm.Adapter = new SampleModelAdapter(list, vm);
            v.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false));
            v.setAdapter(vm.Adapter);
        }
//        ArrayList<String> res = new ArrayList<>();
//        for (SampleModel m:
//                list) {
//            res.add(m.toString());
//        }
//
//        if(vm.Adapter == null){
////            vm.Adapter = new ArrayAdapter<String>(v.getContext(), R.layout.item_string,R.id.txtItem,res);
////            v.setAdapter(vm.Adapter);
//
//        }
//        else
//        {
////            vm.Adapter.clear();
////            vm.Adapter.addAll(res);
//        }
    }

    public FirstVM() {
        SomeNum = new ObservableField<>();
        Name = new ObservableField<>();
        ListOfThings = new ObservableArrayList<>();

        ListOfThings.add(new SampleModel("SEfe", 30));
        ListOfThings.add(new SampleModel("SEfe", 30));
        ListOfThings.add(new SampleModel("SEfe", 30));

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        Observable.timer(0, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Long>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long num) {
                        Name.set(num.toString());
                        ListOfThings.add(new SampleModel("SEfe 1" + num.toString(), num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 2", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                        ListOfThings.add(new SampleModel("SEfe 3", num.intValue()));
                    }
                });
    }

    @Override
    public void clicked(SampleModel model) {
        Log.d("VM", "CLICKKKKKED");
    }
}
