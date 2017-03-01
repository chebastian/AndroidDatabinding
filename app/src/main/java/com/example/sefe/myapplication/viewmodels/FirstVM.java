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
import com.example.sefe.myapplication.adapters.ITestRepo;
import com.example.sefe.myapplication.adapters.SMAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sefe on 1/18/2017.
 */

public class FirstVM extends BaseObservable implements SampleModel.ClickedListener {
    public final ObservableField<Integer> SomeNum;
    public final ObservableField<String> Name;
    public ObservableArrayList<SampleModel> ListOfThings;
    public ObservableField<ITestRepo> TheRepo;
    //    public ArrayAdapter<String> Adapter = null;
//    public SampleModelAdapter Adapter;
    public SMAdapter adapter;

    @BindingAdapter({"bind:listItems", "bind:viewmodel"})
    public static void thingsList(RecyclerView v, ObservableArrayList<SampleModel> list, FirstVM vm) {
        {
            final ArrayList<SampleModel> finalList = list;
            vm.adapter = new SMAdapter(vm.TheRepo.get());

            v.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));
            v.setAdapter(vm.adapter);
        }
    }

    public FirstVM() {
        SomeNum = new ObservableField<>();
        Name = new ObservableField<>();
        ListOfThings = new ObservableArrayList<>();
        TheRepo = new ObservableField<ITestRepo>(new RepoImpl());
    }

    @Override
    public void clicked(SampleModel model) {
        Log.d("VM", "CLICKKKKKED");
    }
}
