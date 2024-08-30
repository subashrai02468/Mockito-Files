package com.subashrai.mockito.mockito_course.business;

import com.subashrai.mockito.mockito_course.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplMockitoInjectMocksTest {

    @Mock
    TodoService todoServiceMock;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void usingAMock() {
        List<String> todos = List.of("Learn Spring", "Learn Spring Boot", "Learn Mockito");
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void usingAMockWithEmptyList() {
        List<String> todos = List.of();
        given(todoServiceMock.retrieveTodos(anyString())).willReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");
        assertEquals(0, filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {
        List<String> todos = List.of("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        given(todoServiceMock.retrieveTodos("Subash")).willReturn(todos);

        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Subash");
        assertEquals(2, filteredTodos.size());
    }

    @Test
    public void letsTestDeleteNow() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        verify(todoServiceMock).deleteTodo("Learn to Dance");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
        verify(todoServiceMock, never()).deleteTodo("Learn Spring");
        verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
    }

    @Test
    public void captureArgument() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoServiceMock.retrieveTodos("subash")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("subash");
        verify(todoServiceMock).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
    }
}