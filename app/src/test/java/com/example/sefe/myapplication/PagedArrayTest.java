package com.example.sefe.myapplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by sefe on 2/16/2017.
 */
public class PagedArrayTest {

    private PagedReadArray<XNode> arr;

    @Before
    public void setup()
    {
        arr = new PagedReadArray<XNode>(20,new PagedReadArray.IPageProvider() {
            @Override
            public List<XNode> loadSection(int start, int length) {
                return null;
            }
        });
    }

    @Test
    public void canLoadPage()
    {
        int pageIndex = 0;
        assertThat(arr.pageIsLoaded(0), is(false));
        arr.loadAdjecentPages(pageIndex);
        assertThat(arr.pageIsLoaded(0), is(true));
    }

    @Test
    public void canConsumePage()
    {
        final boolean[] test = {false};
        arr.loadAdjecentPages(0);
        try{
            arr.getPage(0, new Consumer<List<XNode>>() {
                @Override
                public void accept(List<XNode> xNodes) throws Exception {
                    test[0] = true;
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        assertThat(test[0],is(true));
    }

}