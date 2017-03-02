package com.example.sefe.myapplication.model;

import com.example.sefe.myapplication.interfaces.IDetailsRepository;
import com.example.sefe.myapplication.viewmodels.NodeIdentifier;

import java.util.List;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by sefe on 2/28/2017.
 */
public class DetailsRepoImpl implements IDetailsRepository {
    private io.reactivex.Observable<DetailsModel> _searchObserver;

    @Override
    public void getDetailsForNode(int id, final ResourceSubscriber<DetailsModel> subscriber) {
        final DetailsModel model = new DetailsModel("First Test");

        new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while(i < 20){
                    subscriber.onNext(MockNode(i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
                subscriber.onComplete();
            }
        }).start();
    }

    @Override
    public void getNodesWithId(List<NodeIdentifier> list, ResourceSubscriber<DetailsModel> subscriber) {

        for (NodeIdentifier id : list) {
            subscriber.onNext(MockNode(id.Identifier));
        }

        subscriber.onComplete();
    }

    @Override
    public void getDetailsForNode(int id, Consumer<DetailsModel> subscriber) {
        try {
            subscriber.accept( MockNode(id) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DetailsModel MockNode(int id)
    {
        return new DetailsModel("Node id: " + id);
    }

    @Override
    public void getNodesWithText(final String newText, final ResourceSubscriber<DetailsModel> subscriber) {

        subscriber.onNext(new DetailsModel((newText)));
        subscriber.onComplete();
    }

    @Override
    public void getIdsForNodeWithParent(NodeIdentifier id, ResourceSubscriber<NodeIdentifier> subscriber) {

        for(int i = 0; i < 20; i++)
        {
            subscriber.onNext(new NodeIdentifier((100 * id.Identifier) + i));
        }

        subscriber.onComplete();
    }

    public io.reactivex.Observable<DetailsModel> getSearchObservable(final String newText)
    {
//        if(_searchObserver == null)
        {
            _searchObserver = io.reactivex.Observable.create(new ObservableOnSubscribe<DetailsModel>() {
                @Override
                public void subscribe(ObservableEmitter<DetailsModel> e) throws Exception {
                    e.onNext(new DetailsModel(newText));
                }
            });
        }

        return _searchObserver;
    }

}
