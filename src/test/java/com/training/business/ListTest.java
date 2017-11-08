package com.training.business;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void mockListSizeReturnsSize2(){
        //We mock the List.class
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
    }

    @Test
    public void mockListSize_ReturnMultipleValues(){
        //We mock the List.class
        List listMock = mock(List.class);

        //here we have two values returned after one another
        //first time is called is 2 returned
        //second time is called is 3 returned
        when(listMock.size()).thenReturn(2).thenReturn(3);
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void mockListGet(){
        //We mock the List.class
        List listMock = mock(List.class);

        //This is our argument matcher
        when(listMock.get(0)).thenReturn("Bulgaria");

        assertEquals("Bulgaria", listMock.get(0));

        //if we don't pass a value a null is returned
        assertEquals(null, listMock.get(1));

        //here we can use Mockito's anyInt() - this means if any integer is passed, the same value will be returned
        //If it is a string we can use anyString()
        when(listMock.get(anyInt())).thenReturn("Bulgaria");

        assertEquals("Bulgaria", listMock.get(0));
        assertEquals("Bulgaria", listMock.get(1));
        assertEquals("Bulgaria", listMock.get(2));
    }

    //In this test we expect a RuntimeException to be returned after execution of the test
    @Test(expected = RuntimeException.class)
    public void mockList_throwAnException(){
        //We mock the List.class
        List listMock = mock(List.class);

        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Exception"));

        listMock.get(2);
    }
}
