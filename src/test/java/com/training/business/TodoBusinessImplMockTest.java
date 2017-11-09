package com.training.business;

import com.training.data.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    public void usingMockito_UsingBDD() {
        //Given
        List<String> mockList = mock(List.class);
        given(mockList.get(anyInt())).willReturn("Test Success");

        //When
        String firstElement = mockList.get(0);

        //Then
        assertThat(firstElement, is("Test Success"));
    }

    /*
    Method under test

    	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> allTodos = todoService.retrieveTodos(user);
		for (String todos : allTodos) {
			if (!todos.contains("Spring")) {
				todoService.deleteTodo(todos);
			}
		}
	}
     */

    @Test
    public void deleteTodosNotRelatedToSpring_usingBDD(){
        //GIVEN

        //This is our mock interface
        TodoService todoServiceMock = mock(TodoService.class);

        //Our test data
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring", "Learn to Dance");

        //When todoServiceMock.retrieveTodos is called, the todos list will be returned
        given(todoServiceMock.retrieveTodos("Test User")).willReturn(todos);

        //This is our TodoBusinessImpl that is instantiated by our todoServiceMock
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //WHEN

        //We call the delete method in TodoBusinessImpl
        //After we run delete method, it will loop through the values in the todos List and if
        //the value does not contain Spring, it will be deleted and
        //todoServiceMock.deleteTodo will be called with that value - in our case Learn to Dance
        todoBusiness.deleteTodosNotRelatedToSpring("Test User");

        //THEN

        //We want to verify that the deleteTodo method is called for Learn to Dance data in the list
        //If we pass other value than "Learn to Dance" the test will fail, because the method is called only
        //with this value
        //We can times(1) so that we verify that this method is called only once with the data provided
//        verify(todoServiceMock).deleteTodo("Learn to Dance");
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");

        //Mockito BDD syntax
//        then(todoServiceMock).should().deleteTodo("Learn to Dance");
//        then(todoServiceMock).should(times(1)).deleteTodo("Learn to Dance");

        //We can verify if a certain method is not called at all with its data
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");

        //Mockito BDD syntax
//        then(todoServiceMock).should(never()).deleteTodo("Learn to Dance");

        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    }


    //We want to capture the argument used when a certain method is called
    @Test
    public void deleteTodosNotRelatedToSpring_usingBDD_argumentCapture(){
        //Declare Argument capture that expects a string
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //GIVEN
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Test User")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //WHEN
        todoBusiness.deleteTodosNotRelatedToSpring("Test User");

        //THEN
        //Here we capture the argument passed to deleteTodo Method called by the todoService
        //Using should() the method is expected to be called only once
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());

        //Now we can assert the value that we captured
        assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
    }

    //We want to capture the arguments used when a certain method is called more than once
    @Test
    public void deleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMethodIsCalledTwiceWithTwoDifferentArguments(){
        //Declare Argument capture that expects a string
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //GIVEN
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn MVC","Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Test User")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        //WHEN
        todoBusiness.deleteTodosNotRelatedToSpring("Test User");

        //THEN
        //Here we capture the argument passed to deleteTodo Method called by the todoService
        //This method will be called twice for the values Learn MVC and Learn to Dance
        //because they don't contain the word Spring
        //We add times(2) because we expect the method to be called twice
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        //Now our Argument Capture will have two values and will be a list
        //We want to test that the size of the stringArgumentCaptor is 2
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
    }
}
