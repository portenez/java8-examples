package io.github.portenez.examples.java8.functional;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

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

    @Test
    public void groupStringsByLengthInTreeMap() {

        final Map<Character, List<String>> grouped =
                Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                    .collect(
                            Collectors.groupingBy(
                                    s-> s.charAt(0),
                                    TreeMap::new,
                                    Collectors.toList()
                            ));


        assertThat(grouped.keySet(), contains('e','f','n','o','s','t'));

        assertThat(grouped.get('e'), is(Arrays.asList("eight")));
        assertThat(grouped.get('f'), is(Arrays.asList("four","five")));
        assertThat(grouped.get('n'), is(Arrays.asList("nine")));
        assertThat(grouped.get('o'), is(Arrays.asList("one")));
        assertThat(grouped.get('s'), is(Arrays.asList("six", "seven")));
        assertThat(grouped.get('t'), is(Arrays.asList("two","three","ten")));

    }

    @Test
    public void groupStringsByLengthInTreeMapWithSets() {

        final Map<Character, Set<String>> grouped =
                Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                        .collect(
                                Collectors.groupingBy(
                                        s-> s.charAt(0),
                                        TreeMap::new,
                                        Collectors.toCollection(TreeSet::new)
                                ));


        assertThat(grouped.keySet(), contains('e','f','n','o','s','t'));


        assertThat(grouped.get('e'), contains("eight"));
        assertThat(grouped.get('f'), contains("five", "four"));
        assertThat(grouped.get('n'), contains("nine"));
        assertThat(grouped.get('o'), contains("one"));
        assertThat(grouped.get('s'), contains( "seven", "six"));
        assertThat(grouped.get('t'), contains("ten", "three", "two"));

    }
}
