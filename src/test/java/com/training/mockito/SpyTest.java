package com.training.mockito;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public void testUsingMock(){
        List arrayListMock = mock(ArrayList.class);
        assertEquals(0, arrayListMock.size());

        //mocks return defaulted size
        given(arrayListMock.size()).willReturn(5);
        assertEquals(5, arrayListMock.size());
    }

    @Test
    public void testUsingSpy(){
        List arrayListSpy = spy(ArrayList.class);
        assertEquals(0, arrayListSpy.size());

        //we add a record in the array list
        arrayListSpy.add("Dummy data");
        verify(arrayListSpy).add("Dummy data");
        verify(arrayListSpy, never()).clear();
        assertEquals(1, arrayListSpy.size());

        arrayListSpy.remove("Dummy data");
        assertEquals(0, arrayListSpy.size());

        //you can override a method with stubbing it
        given(arrayListSpy.size()).willReturn(5);
        assertEquals(5, arrayListSpy.size());
    }
}
