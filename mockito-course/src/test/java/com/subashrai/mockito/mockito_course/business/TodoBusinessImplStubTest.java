package com.subashrai.mockito.mockito_course.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.subashrai.mockito.mockito_course.data.api.TodoService;
import com.subashrai.mockito.mockito_course.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

    @Test
    public void usingAStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Subash");
        assertEquals(2, todos.size());
    }


}