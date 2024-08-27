package com.subashrai.mockito.mockito_course.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ListTest {

    @Test
    public void letsMockListSize() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        assertEquals(10, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size()); // First Call
        assertEquals(20, list.size()); // Second Call
    }

    @Test
    public void letsMockListGet() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("in28Minutes");
        assertEquals("in28Minutes", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void letsMockListGetToThrowException() {
        List<String> list = mock(List.class);
        when(list.get(Mockito.anyInt())).thenThrow(
                new RuntimeException("Something went wrong"));
        assertThrows(RuntimeException.class, () -> list.get(0));
    }

    @Test
    public void letsMockListGetWithAny() {
        List<String> list = mock(List.class);
        Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes");
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        assertEquals("in28Minutes", list.get(0));
        assertEquals("in28Minutes", list.get(1));
    }

    @Test
    public void bddAliases_UsingGivenWillReturn() {
        List<String> list = mock(List.class);

        //given
        given(list.get(Mockito.anyInt())).willReturn("in28Minutes");

        //then
        assertThat("in28Minutes").isEqualTo(list.get(0));
        assertThat("in28Minutes").isEqualTo(list.get(1));
    }
}