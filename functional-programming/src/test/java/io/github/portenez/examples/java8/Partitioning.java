package io.github.portenez.examples.java8;


import org.junit.Test;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Shows how to use partitioning
 *
 * Created by vgarcia on 1/1/16.
 */
public class Partitioning {


    @Test
    public void partitioning() {

        final Map<Boolean, List<String>> partitioned =
                Stream.of("ducks", "falcon", "ostrich")
                        .collect(Collectors.partitioningBy(s -> s.startsWith("o")));

        assertThat(partitioned.get(true), containsInAnyOrder("ostrich"));
        assertThat(partitioned.get(true), hasSize(1));
        assertThat(partitioned.get(false), containsInAnyOrder("ducks", "falcon"));
        assertThat(partitioned.get(false), hasSize(2));
    }
}
