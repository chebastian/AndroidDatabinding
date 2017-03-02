package com.example.sefe.myapplication.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.model.DetailsModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by sefe on 2/28/2017.
 */

public class InspectionViewModel {

    public ObservableField<String> Name;
    public ObservableArrayList<DetailsModel> DetailsList;
    public IDetailsRepository repo;
    public ObservableArrayList<String> Details;
    public ObservableField<Boolean> IsEndShowing;
    private ResourceSubscriber<DetailsModel> _detailsSubscriber;

    public InspectionViewModel(int id, IDetailsRepository repo)
    {
        init();
        this.repo = repo;
        Name = new ObservableField<>(String.format("Inspection of : %s",id));
        this.repo.getDetailsForNode(id, getNodeSubscriber());
    }

    public InspectionViewModel(IDetailsRepository repo, List<NodeIdentifier> nodes)
    {
        this.repo = repo;
        init();
        repo.getNodesWithId(nodes,getDetailsSubscriber());
    }

    private void init()
    {
        Details = new ObservableArrayList<>();
        DetailsList = new ObservableArrayList<>();
        IsEndShowing = new ObservableField<>(false);
        _detailsSubscriber = getDetailsSubscriber();
    }


    private ResourceSubscriber<DetailsModel> getNodeSubscriber()
    {
        return new ResourceSubscriber<DetailsModel>() {
            @Override
            public void onNext(DetailsModel detailsModel) {
                Details.add(detailsModel.Name.get());
                DetailsList.add(detailsModel);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public void SearchText(final String newText) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                DetailsList.clear();
                e.onNext(newText);
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("T", "search: " + s);
                repo.getNodesWithText(s, _detailsSubscriber);
            }
        });

    }

    private ResourceSubscriber<DetailsModel> getDetailsSubscriber()
    {
        return new ResourceSubscriber<DetailsModel>(){

            @Override
            public void onNext(DetailsModel detailsModel) {
                DetailsList.add(detailsModel);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

}
