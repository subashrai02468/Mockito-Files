package com.subashrai.mockito.mockito_course.data.api;


import java.util.Arrays;
import java.util.List;


import com.subashrai.mockito.mockito_course.data.api.TodoService;

public class TodoServiceStub implements TodoService {
    public List<String> retrieveTodos(String user) {
        // if user is subash then return 2 todos
        // if user is ranga then return 1 todo

        return Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
    }

    public void deleteTodo(String todo) {

    }


}