package com.example.sefe.myapplication;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sefe on 2017-01-31.
 */

public class XryDbReader implements IXryFileRepository {
    private final Context _ctx;
    private LocalDbReader reader;

    public XryDbReader(Context ctx)
    {
        _ctx = ctx;
        reader = new LocalDbReader(_ctx);
        try {
            reader.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getAvailableViews() {

        Cursor c = reader.runQuery("SELECT *, count(*) as size from views group by viewid");
        int viewNameIndex = c.getColumnIndex("viewid");
        ArrayList<String> res = new ArrayList<>();

        while(c.moveToNext())
        {
            res.add(c.getString(viewNameIndex));
        }

        return res;
    }

    @Override
    public ArrayList<XNode> getNodesInView(String viewName) {
        ArrayList<XNode> res = new ArrayList<>();
        Cursor c = reader.runQuery("select views.nodeid, properties.proptype, properties.value from views join properties on properties.nodeid = views.nodeid where views.viewid = '" + viewName + "'");

        int propety_name_id = c.getColumnIndex("properties.proptype");
        int property_value_id = c.getColumnIndex("properties.value");
        int node_id = c.getColumnIndex("nodes.nodeid");
        int currentID = 0;
        HashMap<Integer,XNode> values = new HashMap<>();

        while(c.moveToNext())
        {
            XNode node = null;
            int nodeid = c.getInt(node_id);
            if(values.containsKey(nodeid))
            {
                node = values.get(nodeid);
                values.remove(node);
            }
            else
            {
                node = new XNode();
            }

            String propName = c.getString(propety_name_id);
            String propVal = c.getString(property_value_id);
            node.Properties.add(new XProperty(propName,propVal));
            values.put(nodeid,node);
        }

        return res;
    }

    @Override
    public ArrayList<XNode> getNodesInView(String contacts_view, int start, int length) {
        return null;
    }

    @Override
    public int getViewSize(String viewName) {
        return 0;
    }
}
