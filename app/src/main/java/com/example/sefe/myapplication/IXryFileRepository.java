package com.example.sefe.myapplication;

import java.util.ArrayList;

/**
 * Created by sefe on 2017-01-17.
 */
public interface IXryFileRepository {
    ArrayList<String> getAvailableViews();
    ArrayList<XNode> getNodesInView(String viewName);
    ArrayList<XNode> getNodesInView(String contacts_view, int start, int length);
    ArrayList<XNode> getNodesContainingPropType(String type);
    ArrayList<XNode> getNodesContainingValue(String value);
    XNode            getNodeAtIndexInView(String viewName, int nodeIndex);
    int              getViewSize(String viewName);
}
