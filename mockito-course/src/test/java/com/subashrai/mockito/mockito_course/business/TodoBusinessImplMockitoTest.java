package com.subashrai.mockito.mockito_course.business;

import com.subashrai.mockito.mockito_course.data.api.TodoService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.*;
import com.subashrai.mockito.mockito_course.data.api.TodoService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class TodoBusinessImplMockitoTest {

    //what is mocking?
    //Mocking is creating objects that simulate the behavior of real objects.
    //Unlike stubs, mocks can be dynamically created from code - at runtime.
    //Mocks offer more functionality than stubbing.
    //You can verify method calls and a lot of other things.

   @Test
    public void usingAMock() {
    // Step 1: Create a mock instance of TodoService
    TodoService todoServiceMock = mock(TodoService.class);

    // Step 2: Define the behavior of the mock
    List<String> todos = List.of("Learn Spring", "Learn Spring Boot", "Learn Mockito");
    given(todoServiceMock.retrieveTodos(anyString())).willReturn(todos);

    // Step 3: Create an instance of TodoBusinessImpl using the mock
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    // Step 4: Call the method and assert the result
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");
    assertEquals(2, filteredTodos.size());
    }

    @Test
    public void usingAMockWithEmptyList() {
        // Step 1: Create a mock instance of TodoService
        TodoService todoServiceMock = mock(TodoService.class);
        // make empty todo list
        List<String> todos = List.of();
        // Step 2: Define the behavior of the mock to return an empty list
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todos);

        // Step 3: Create an instance of TodoBusinessImpl using the mock
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // Step 4: Call the method and assert the result
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");
        assertEquals(0, filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {
        // Given - Setup
        TodoService todoServiceMock = mock(TodoService.class);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Subash")).willReturn(todos);

        // When - Actual method call
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");

        // Then - Assert
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void letsTestDeleteNow() {

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        verify(todoService).deleteTodo("Learn to Dance");

        verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");


        verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

        verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast

    }


    @Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("subash")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("subash");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }





}