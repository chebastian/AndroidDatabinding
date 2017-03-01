package com.example.sefe.myapplication.viewmodels;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.model.DetailsModel;

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
        this.repo = repo;
        Details = new ObservableArrayList<>();
        Name = new ObservableField<>(String.format("Inspection of : %s",id));
        DetailsList = new ObservableArrayList<>();
        IsEndShowing = new ObservableField<>(false);
        this.repo.getDetailsForNode(id, getNodeSubscriber());
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

    public void SearchText(String newText) {

        DetailsList.clear();
        repo.getNodesWithText(newText, _detailsSubscriber);

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
