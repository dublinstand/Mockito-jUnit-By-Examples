package com.training.junit.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

//here we say that we use the parametrized class to populate the data
@RunWith(Parameterized.class)
public class StringHelperParameterizeTest {

    private StringHelper helper = new StringHelper();

    //these are our two private strings, which are populated through the constructor and Parameterized.class
    //using the @Parameters data from testCondition method
    private String input;
    private String expectedOutput;

    //our public constructor for filling our parameters from the Collection in @Parameters
    public StringHelperParameterizeTest(String input, String expectedOutput){
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    //This is our test data
    @Parameterized.Parameters
    public static Collection testCondition(){
        String[][] expectedOutput = {
                {"AACD", "CD"},
                {"ACD", "CD"},
                {"CDAA", "CDAA"},
                {"ACDB", "CDB"}
        };
        return Arrays.asList(expectedOutput);
    }

    // AACD => CD ; CDAA => CDAA ; ACDB => CDB
    //we use only one test class and we have as much tests run as much test sets we have in the Collection
    @Test
    public void truncateAInFirst2Positions() throws Exception {
        assertEquals(expectedOutput, helper.truncateAInFirst2Positions(input));
    }
}