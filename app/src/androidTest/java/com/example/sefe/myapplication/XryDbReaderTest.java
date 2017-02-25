package com.example.sefe.myapplication;

import android.support.test.InstrumentationRegistry;


import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by sefe on 2/14/2017.
 */
@RunWith(Enclosed.class)
public class XryDbReaderTest {

    private static final String AVAILABLE_VIEW = "NID_VIEW_CALLS";

    public static class GetNodeAtId
    {
        private XryDbReader _reader;
        @Before
        public void setup()
        {
            _reader = new XryDbReader(InstrumentationRegistry.getTargetContext());
        }

        @Test
        public void returnsCachedNodeAtIndex() throws Exception {
            XNode n = _reader.getNodeAtIndexInView(AVAILABLE_VIEW, 2);
            assertTrue("Should be cached", _reader.nodeIsCached(AVAILABLE_VIEW,2));
            assertTrue("should not be null", n != null);
        }

        @Test
        public void runsLoadCacheWhenReadingOOB()
        {
            XNode n = _reader.getNodeAtIndexInView(AVAILABLE_VIEW, 2);
            assertThat(_reader.nodeIsCached(AVAILABLE_VIEW,500), is(false));

            assertThat(_reader.nodeIsCached(AVAILABLE_VIEW,10), is(true));
            assertThat(_reader.nodeIsCached(AVAILABLE_VIEW,10), is(true));

        }
    }
}