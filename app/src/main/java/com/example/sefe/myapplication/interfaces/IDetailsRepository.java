package com.example.sefe.myapplication.interfaces;

import com.example.sefe.myapplication.model.DetailsModel;
import com.example.sefe.myapplication.viewmodels.NodeIdentifier;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by sefe on 2/28/2017.
 */
public interface IDetailsRepository {
    void getDetailsForNode(int id, ResourceSubscriber<DetailsModel> subscriber);
    void getDetailsForNode(int id, Consumer<DetailsModel> subscriber);
    void getNodesWithText(String newText, ResourceSubscriber<DetailsModel> subscriber);

    void getNodesWithId(List<NodeIdentifier> list, ResourceSubscriber<DetailsModel> subscriber);
    void getIdsForNodeWithParent(NodeIdentifier id, ResourceSubscriber<NodeIdentifier> subscriber);
}
