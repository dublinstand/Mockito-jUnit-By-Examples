package com.training.business;

import com.training.data.api.TodoService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockTest {

    //What is mocking?
    //Mocking is creating objects that simulate the behavior of real objects
    //Unlike stubs, mock can be dynamically created from code - at runtime
    //Mocks offer more functionality than stubbing
    //You can verify method calls and a lot of other things

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){
        //With mocking dynamically have the method return a given value

        //Here we create a mock object of TodoService.class (we can mock also an interface)
        TodoService todoServiceMock = mock(TodoService.class);

        //This will be our data to be returned when the method is called
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring", "Learn to Dance");

        //Here we specify that when our mock service calls retrieveTodos and pass in "Dummy user"
        //as user name, then we return our todos List
        when(todoServiceMock.retrieveTodos("Dummy user")).thenReturn(todos);

        //we instantiate our TodoBusinsessImpl class that we want to test and pass the todoServiceStub we created
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        //here we pass our mocked TodoBusinessImpl that gets a list of 3 records from todoService.retrieveTodos(user)
        //and two of them contain Spring, so filteredTodos will have a size of 2
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy user");

        assertEquals("We expect the result returned by our stub is 2", 2, filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_withEmptyList(){
        //With mocking dynamically have the method return a given value

        //Here we create a mock object of TodoService.class (we can mock also an interface)
        TodoService todoServiceMock = mock(TodoService.class);

        //This will be our data to be returned when the method is called
        //In this case we return an empty list
        List<String> todos = Arrays.asList();

        //Here we specify that when our mock service calls retrieveTodos and pass in "Dummy user"
        //as user name, then we return our todos List
        when(todoServiceMock.retrieveTodos("Dummy user")).thenReturn(todos);

        //we instantiate our TodoBusinsessImpl class that we want to test and pass the todoServiceStub we created
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy user");

        assertEquals(0, filteredTodos.size());
    }
}
