package com.subashrai.mockito.mockito_course.business;

import com.subashrai.mockito.mockito_course.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;
//TodoBusinessImpl is SUT(system under test)
//TodoService is dependency, which is not implemented yet
//how to implement TodoBusinessImpl without TodoService by mocking it



public class TodoBusinessImpl {
    private TodoService todoService;

    TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo : allTodos) {
            if (todo.contains("Spring")) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }


    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo : allTodos) {
            if (!todo.contains("Spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }
}