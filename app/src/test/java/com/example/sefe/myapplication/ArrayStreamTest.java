package com.example.sefe.myapplication;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sefe on 2017-02-08.
 */

public class ArrayStreamTest {
    private BufferStream buff;

    private int index_inside_buffer = 309;
    private int index_inside_buffer2 = 291;

    private int index_outside_start_of_buffer = 279;
    private int index_outside_end_of_buffer = 311;
    private int index_outside_whole_buffer = 2000;
    private boolean wasReloaded;



    @Before
    public void setup()
    {
        wasReloaded = false;
        buff = new BufferStream(new BufferStream.IBufferListener() {
            @Override
            public void reloadBufferAtWithSize(int start, int length, IBufferLoadCompleteListener listener) {
                wasReloaded = true;
                listener.onComplete();
            }
        });
        buff.setBufferLoadAtPercent(0.2f);
        buff.setCurrentIndex(300);
        buff.setBufferSize(50);
    }
    @Test
    public void canCreateBufferedArray(){
    }

    @Test
    public void readingFromInsideBufferDoesNotLoad()
    {
        buff.setCurrentIndex(300);
        Assert.assertFalse("should need reload",buff.needsLoad(index_inside_buffer));
        Assert.assertFalse("should need reload",buff.needsLoad(index_inside_buffer2));
    }

    @Test
    public void readingFromOutsizeDoesNeedReload()
    {
        Assert.assertTrue("should need reload",buff.needsLoad(index_outside_end_of_buffer));
        Assert.assertTrue("should need reload",buff.needsLoad(index_outside_start_of_buffer));
        Assert.assertTrue("should need reload",buff.needsLoad(index_outside_whole_buffer));
    }

    @Test
    public void reloadAffectsScope()
    {
        Assert.assertTrue("should need reload",buff.needsLoad(279));
        buff.setCurrentIndex(280);
        Assert.assertFalse("should not need reload after index change",buff.needsLoad(279));
        Assert.assertTrue("should need reload again after index",buff.needsLoad(10));
    }

    @Test
    public void callsReloadOnNeedsLoad()
    {
        int oldIndex = buff.CurrentMidpoint();
        buff.setCurrentReadingIndex(index_outside_end_of_buffer);
        Assert.assertTrue("No reload after read", wasReloaded);
        int newIndex = buff.CurrentMidpoint();
        Assert.assertFalse("index should have moved", oldIndex == newIndex);
    }

    @Test
    public void callsReloadOnOutOfBufferRead()
    {
        int oldIndex = buff.CurrentMidpoint();
        buff.setCurrentReadingIndex(index_outside_whole_buffer);
        Assert.assertTrue("No reload after read", wasReloaded);
        int newIndex = buff.CurrentMidpoint();
        Assert.assertFalse("index should have moved", oldIndex == newIndex);
    }

}
