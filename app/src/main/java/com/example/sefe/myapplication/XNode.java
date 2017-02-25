package com.example.sefe.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sefe on 2/1/2017.
 */
public class XNode {
    private final int _nodeID;
    public List<XProperty> Properties;

    public XNode(int id)
    {
        _nodeID = id;
        Properties = new ArrayList<>();
    }

    public XNode()
    {
        Properties = new ArrayList<>();
        _nodeID = -1;
    }

    public int NodeId() {
        return _nodeID;
    }
}
