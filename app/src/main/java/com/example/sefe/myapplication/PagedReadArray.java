package com.example.sefe.myapplication;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.functions.Consumer;

/**
 * Created by sefe on 2/16/2017.
 */
public class PagedReadArray<T>{

    private final IPageProvider _pageProvider;
    private ArrayList<T> _arr;
    private SparseArray<Page<T>> pages;
    private int PageSize;

    public PagedReadArray(int pageSize,IPageProvider pageProvider)
    {
        _arr = new ArrayList<>();
        _pageProvider = pageProvider;
        PageSize = pageSize;
        pages = new SparseArray<>();
    }

    public void loadAdjecentPages(int pageIndex) {
        tryLoadPage(pageIndex-1);
        tryLoadPage(pageIndex);
        tryLoadPage(pageIndex+1);
    }

    private void tryLoadPage(int i) {
        if(i >= 0)
        {
            if(!pageIsLoaded(i))
                loadPage(i);
        }
    }

    private void loadPage(int i) {
        Page p = new Page();
        p.list = _pageProvider.loadSection(PageSize * i, PageSize);
        p.index = i;
        pages.put(i,p);
    }

    public void getPage(int index, Consumer<List<T>> consumer) throws Exception {
        consumer.accept(pages.get(index).list);
    }

    public boolean pageIsLoaded(int i) {
        return pages.get(i,null) != null;
    }

    private class Page<T> {
        public int index;
        public List<T> list;
    }

    public  interface IPageProvider<T> {
        List<T> loadSection(int start, int length);
    }
}
