package com.training.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest {
    //Prepare for test => SystemUnderTest.class and not ArrayList.class
    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Mock
    ArrayList mockList;

    @Test
    public void methodForMockingConstructorForArrayList() throws Exception {
        List<Integer> testData = Arrays.asList(1, 2, 3);

        //We have our mockList.size return 10
        when(mockList.size()).thenReturn(10);

        //After we instantiate our ArrayList in the method under test, it will return the values
        //returned by the mockList, which is size() = 10
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

        int size = systemUnderTest.methodUsingAnArrayListConstructor();

        assertEquals(10, size);
    }
}
