package com.subashrai.mockito.mockito_course.data.api;

import java.util.List;

//create todoservice stub
//test todobusinessimpl using todoservice stub


// External Service - Lets say this comes from WunderList
public interface TodoService {
    public List<String> retrieveTodos(String user);
    void deleteTodo (String todo);

}