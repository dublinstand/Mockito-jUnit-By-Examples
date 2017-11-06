package com.training.junit.helper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringHelperTest {

    private StringHelper helper = new StringHelper();

    @Test
    public void truncateAInFirst2Positions_AinFirst2Positions() throws Exception {
        // AACD => CD ; CDAA => CDAA ; ACDB => CDB
        String result = helper.truncateAInFirst2Positions("AACD");
        String expected = "CD";
        assertEquals(expected, result);
    }

    @Test
    public void truncateAInFirst2Positions_AinFirstPosition() throws Exception {
        // AACD => CD ; CDAA => CDAA ; ACDB => CDB
        assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
    }
}