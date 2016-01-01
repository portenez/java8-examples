package io.github.portenez.examples.java8.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Examples of how to use grouping
 *
 * Created by vgarcia on 1/1/16.
 */
public class GroupingWithCollectors {


    @Test
    public void groupStringByLength() {

        final Map<Integer, List<String>> grouped =
            Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                    .collect(Collectors.groupingBy(String::length));


        assertThat(grouped.get(3), is(Arrays.asList("one", "two", "six", "ten")));
        assertThat(grouped.get(4), is(Arrays.asList("four", "five", "nine")));
        assertThat(grouped.get(5), is(Arrays.asList("three", "seven", "eight")));
    }
}
