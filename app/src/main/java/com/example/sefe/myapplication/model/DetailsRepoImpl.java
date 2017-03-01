package com.example.sefe.myapplication.model;

import android.database.Observable;
import android.util.Log;

import com.example.sefe.myapplication.interfaces.IDetailsRepository;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;
import io.reactivex.subscribers.SafeSubscriber;

/**
 * Created by sefe on 2/28/2017.
 */
public class DetailsRepoImpl implements IDetailsRepository {
    @Override
    public void getDetailsForNode(int id, final ResourceSubscriber<DetailsModel> subscriber) {
        final DetailsModel model = new DetailsModel("First Test");

        new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while(i < 20){
                    subscriber.onNext(model);
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
    public void getDetailsForNode(int id, Consumer<DetailsModel> subscriber) {
        try {
            subscriber.accept(new DetailsModel("THIS IS ONE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getNodesWithText(final String newText, final ResourceSubscriber<DetailsModel> subscriber) {

        io.reactivex.Observable.create(new ObservableOnSubscribe<DetailsModel>() {
            @Override
            public void subscribe(final ObservableEmitter<DetailsModel> e) throws Exception {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DetailsModel model = new DetailsModel(newText);
                        e.onNext(model);

//                        int i = 0;
//                        while(i < 20){
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            i++;
//                        }
                        e.onComplete();
                    }
                }).start();
            }
        }).debounce(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<DetailsModel>() {
            @Override
            public void accept(DetailsModel detailsModel) throws Exception {
                Log.d("TTT", "next" + detailsModel.Name);
                subscriber.onNext(detailsModel);
            }
        });
    }
}
