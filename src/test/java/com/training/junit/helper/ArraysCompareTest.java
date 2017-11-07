package com.training.junit.helper;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ArraysCompareTest {

    //Arrays.sort
    @Test
    public void testArraySort_RandomArray(){
        int[] numbers = {12,3,4,1};
        int[] expected = {1,3,4,12};

        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);
    }

    @Test(expected = NullPointerException.class)
    public void testArraySort_ThrowNullPointerException(){
        int[] numbers = {};
        Arrays.sort(numbers);
    }

    @Test(timeout = 1000)
    public void testSort_Performance(){
        int[] numbers = {1,2,3};

        for (int i = 0; i < 1000000; i++) {
            numbers[0] = i;
            Arrays.sort(numbers);
        }
    }
}
