package com.example.sefe.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sefe on 2017-01-31.
 */

public class XryDbReader implements IXryFileRepository,BufferStream.IBufferLoader {
    private LocalDbReader reader;
    private HashMap<String,Cursor> CursorCache;
    private HashMap<String,Integer> _countCache;
    private int PreloadBufferSize;
    private int CurrentPreloadMax;
    private int CurrentPreloadMin;
    private boolean IsLoading;

    private  static  String COL_PROPTYPE = "proptype";
    private  static  String COL_PROPVALUE = "value";
    private  static  String COL_NODE_ID = "nodeid";
    private  static final String COL_PROP_NODE_ID = "nodeid";
    private int BuffStart;
    private int BuffEnd;
    private @NonNull  SparseArray<XNode> CachedNodes;
    private @NonNull BufferStream _stream;
    private String _currentView;

    public XryDbReader(Context ctx)
    {
        reader = new LocalDbReader(ctx);
        try {
            reader.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CachedNodes = new SparseArray<>();

        PreloadBufferSize = 100;
        CurrentPreloadMin = -1;
        CurrentPreloadMax = -1;
        CursorCache = new HashMap<>();
        _countCache = new HashMap<>();
        IsLoading = false;

        _stream = new BufferStream(this);
        _stream.setBufferSize(20);
        _stream.setBufferLoadAtPercent(0.8f);
        _stream.setCurrentIndex(0);
    }

    private void SetBufferStartIndex(int index,int totalsize)
    {
        CurrentPreloadMax = Math.min(index + PreloadBufferSize, totalsize);
        CurrentPreloadMin = Math.max(index - PreloadBufferSize,0);
        BuffStart = Math.max(index - PreloadBufferSize*2, 0);
        BuffEnd = Math.min(index + PreloadBufferSize*2, totalsize);
    }

    private void appendPropertyFromCursor(Cursor c, XNode theNode, int propety_name_id, int property_value_id)
    {
        String propName = c.getString(propety_name_id);
        String propVal = c.getString(property_value_id);
        theNode.Properties.add(new XProperty(propName,propVal));
    }

    @Override
    public ArrayList<String> getAvailableViews() {

        String.format("asd {}", 1);
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

        Cursor c = null;
        if(CursorCache.containsKey(viewName))
        {
            c =CursorCache.get(viewName);
        }
        else{
            c = reader.runQuery("select views.nodeid, properties.proptype, properties.value from views join properties on properties.nodeid = views.nodeid where views.viewid = '" + viewName + "' group by views.nodeid");
        }


        Cursor props =  getPropertiesFromResultsWithNodeid(c);
        return (ArrayList<XNode>)createNodesFromCursorOfProperties(props);
    }

    private static <C> List<C> asList(SparseArray<C> sparseArray) {
        if (sparseArray == null)
            return null;

        List<C> theList = new ArrayList<C>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++)
        {
            theList.add(sparseArray.valueAt(i));
        }

        return theList;
    }

    public Cursor getPropertiesFromResultsWithNodeid(Cursor nodes)
    {
        int node_id = nodes.getColumnIndex(COL_NODE_ID);
        ArrayList<Integer> node_ids = new ArrayList<>();
        while(nodes.moveToNext())
        {
            node_ids.add(nodes.getInt(node_id));
        }

        String strIds = node_ids.toString();
        strIds = strIds.substring(1,strIds.length()-1);


        SparseArray<XNode> values = new SparseArray<>();
        String sql = "SELECT * from properties where nodeid IN (" + strIds + ")";
        Cursor props = reader.runQuery(sql);
        return props;
    }

    public XNode NodeFromCursorWithId(Cursor cursorWithNodeId)
    {
        Cursor props = getPropertiesFromResultsWithNodeid(cursorWithNodeId);
        return createNodesFromCursorOfProperties(props).get(0);
    }

    public List<XNode> createNodesFromCursorOfProperties(Cursor props)
    {
        SparseArray<XNode> values = new SparseArray<>();
        {
            int proptype_id = props.getColumnIndex(COL_PROPTYPE);
            int propvalue_id = props.getColumnIndex(COL_PROPVALUE);
            int node_id = props.getColumnIndex(COL_NODE_ID);
            while (props.moveToNext())
            {
                int theId = props.getInt(node_id);
                if(CachedNodes.get(theId) != null)
                {
                    Log.d("CACHE", "In Cache" + theId);
                    if(values.get(theId) != null)
                        break;

                    values.put(theId,CachedNodes.get(theId));
                    break;
                }
                XNode theNode = new XNode(theId);
                {
                    if (values.get(theId) == null) {
                        theNode = new XNode(theId);
                        values.put(theId, theNode);
                    }
                    appendPropertyFromCursor(props, values.get(theId), proptype_id, propvalue_id);
//                    Log.d("CACHE", "Not Cache" + theId + " loading: " + IsLoading);
                }
            }
        }
        return (List<XNode>) XryDbReader.asList(values);
    }

    @Override
    public ArrayList<XNode> getNodesInView(String viewName, int start, int length) {
        Cursor c = null;
        if(CursorCache.containsKey(viewName))
        {
            c =CursorCache.get(viewName);
        }
        else{
            c = reader.runQuery("SELECT * from nodes where nodeid in (SELECT nodeid from views where viewid = '" + viewName + "') LIMIT " +start+", " + length);
        }

        Cursor props = getPropertiesFromResultsWithNodeid(c);
        return (ArrayList<XNode>) createNodesFromCursorOfProperties(props);
    }

    public Cursor getViewCursor(String viewName)
    {
        Cursor c = null;
        if(CursorCache.containsKey(viewName))
        {
            c =CursorCache.get(viewName);
        }
        else{
            c = reader.runQuery("SELECT *, nodeid as _id from nodes where nodeid in (SELECT nodeid from views where viewid = '" + viewName + "')");
        }

        return c;
    }

    @Override
    public ArrayList<XNode> getNodesContainingPropType(String type) {
        return null;
    }

    @Override
    public ArrayList<XNode> getNodesContainingValue(String value) {
        ArrayList<XNode> res = new ArrayList<>();
        Cursor c = reader.runQuery("SELECT nodeid from properties where value LIKE '%" + value + "%' group by nodeid");
        int node_id = c.getColumnIndex("nodeid");
        while(c.moveToNext())
        {

            int theId = c.getInt(node_id);

            String query = "SELECT * from properties where nodeid = '" + theId + "'";
            Cursor prop_c = reader.runQuery(query);

            XNode theNode = new XNode();
            int proptype_id = prop_c.getColumnIndex(COL_PROPTYPE);
            int propvalue_id = prop_c.getColumnIndex(COL_PROPVALUE);
            while(prop_c.moveToNext())
            {
                appendPropertyFromCursor(prop_c,theNode,proptype_id, propvalue_id);
            }
            res.add(theNode);
        }

        return res;
    }

    public boolean nodeIsCached(String viewName, int index)
    {
        return CachedNodes.get(index) != null;
    }

    @Override
    public XNode getNodeAtIndexInView(String viewName, int nodeIndex) {

        _currentView = viewName;
        _stream.setCurrentReadingIndex(nodeIndex);

        if(CachedNodes.get(nodeIndex) != null)
            return CachedNodes.get(nodeIndex);

        Cursor c = null;
        if(CursorCache.containsKey(viewName))
        {
            c =CursorCache.get(viewName);
        }
        else{
            c = reader.runQuery("SELECT * from nodes where nodeid in (SELECT nodeid from views where viewid = '" + viewName + "') LIMIT " +nodeIndex+", 1");
        }

        doPreloadOfIndexNeighbours(viewName,nodeIndex);

        ArrayList<XNode> res = (ArrayList<XNode>) createNodesFromCursorOfProperties((getPropertiesFromResultsWithNodeid(c)));
        return res.get(0);
    }

    private void doPreloadOfIndexNeighbours(String viewName, int nodeIndex) {
        boolean isWihtinTresh =  nodeIndex > CurrentPreloadMax || nodeIndex < CurrentPreloadMin;
        if(isWihtinTresh && !IsLoading)
        {
            Log.d("CACHE", "Start to expand cache" + BuffStart + " " + BuffEnd);
            SetBufferStartIndex(nodeIndex,getViewSize(viewName));
            cacheSection(viewName, BuffStart, BuffEnd - BuffStart);
        }
    }

    private void cacheSection(final String viewName, final int start, final int length) {

    }

    @Override
    public int getViewSize(String viewName) {
        if(!_countCache.containsKey(viewName))
        {
            Cursor c = reader.runQuery("SELECT * from views where viewid = '" + viewName + "'");
            _countCache.put(viewName,c.getCount());
        }

        return _countCache.get(viewName);
    }

    @Override
    public void reloadBufferAtWithSize(final int start, final int length, final IBufferLoadCompleteListener onComplete) {

    }
}

