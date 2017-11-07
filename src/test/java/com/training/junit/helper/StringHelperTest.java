package com.training.junit.helper;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringHelperTest {

    private StringHelper helper = new StringHelper();

    // AACD => CD ; CDAA => CDAA ; ACDB => CDB
    @Test
    public void truncateAInFirst2Positions_AinFirst2Positions() throws Exception {
        String result = helper.truncateAInFirst2Positions("AACD");
        String expected = "CD";
        assertEquals(expected, result);
    }

    @Test
    public void truncateAInFirst2Positions_AinFirstPosition() throws Exception {
        assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
    }

    @Test
    public void truncateAInFirst2Positions_2AsInLastPosition() throws Exception {
        assertEquals("CDAA", helper.truncateAInFirst2Positions("CDAA"));
    }

    @Test
    public void truncateAInFirst2Positions_AInFirstPosition_2() throws Exception {
        assertEquals("CDB", helper.truncateAInFirst2Positions("ACDB"));
    }

    //ABCD => false ; ABAB => true ; AB => true ' A => false
    @Test
    public void areFirstAndLastTwoCharactersTheSame_BasicNegativeScenario(){
        assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
    }

    @Test
    public void areFirstAndLastTwoCharactersTheSame_BasicPositiveScenario(){
        assertTrue(helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
    }
}