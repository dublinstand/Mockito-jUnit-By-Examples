package com.training.business;

import com.training.data.api.TodoService;
import com.training.data.api.TodoServiceStub;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TodoBusinessImplStubTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAStub(){
        //instantiate the TodoServiceStub that returns the interface
        TodoService todoServiceStub = new TodoServiceStub();

        //we instantiate our TodoBusinsessImpl class that we want to test and pass the todoServiceStub we created
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

        //here we pass our stubbed TodoBusinessImpl that gets a list of 3 records from todoService.retrieveTodos(user)
        //and two of them contain Spring, so filteredTodos will have a size of 2
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy user");

        assertEquals("We expect the result returned by our stub is 2", 2, filteredTodos.size());
    }
}
