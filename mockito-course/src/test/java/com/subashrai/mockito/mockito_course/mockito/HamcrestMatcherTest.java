package com.subashrai.mockito.mockito_course.mockito;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HamcrestMatcherTest {

    @Test
    public void basicHamcrestMatchers() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(200)));

        // String
        assertThat("", isEmptyString());
        assertThat(null, isEmptyOrNullString());

        // Array
        Integer[] marks = { 1, 2, 3 };
        assertThat(marks, arrayWithSize(3));
        assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));
    }
}