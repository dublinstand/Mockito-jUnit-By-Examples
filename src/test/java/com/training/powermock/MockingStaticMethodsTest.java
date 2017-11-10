package com.training.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodsTest {
    //We need to use a specific runner when we use Mockito and PowerMock together
    //We need to initialize our static method - UtilityClass.class
    //Then we can start mocking

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void methodCallingAStaticMethod_withMockito_andPowerMock(){
        List<Integer> testData = Arrays.asList(1, 2, 3);

        //We mock our data for .retriveAllStats() method
        when(dependency.retrieveAllStats()).thenReturn(testData);

        //Now we need to use PowerMock to mock the static method in the class
        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(150);

        //run our test method and return the value
        int result = systemUnderTest.methodCallingAStaticMethod();

        //assert that correct value is returned by our mock
        assertEquals(150, result);

        //we verify that dependency.retrieveAllStats() is called - this is done by mockito
        verify(dependency, times(1)).retrieveAllStats();

        //Now we want to verify that the static method is called - this is done by PowerMock
        //We tell PowerMockito to check next static call and will check the parameter that is called with to be 6
        //if we pass 5 instead of 6, it will fail
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);
    }
}
