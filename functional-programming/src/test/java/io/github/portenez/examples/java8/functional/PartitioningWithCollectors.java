package io.github.portenez.examples.java8.functional;


import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Shows how to use partitioningBy
 * <p>
 * Created by vgarcia on 1/1/16.
 */
public class PartitioningWithCollectors {


    @Test
    public void partitioningBy() {

        final Map<Boolean, List<String>> partitioned =
                Stream.of("duck", "falcon", "ostrich")
                        .collect(
                                Collectors.partitioningBy(s -> s.startsWith("o")));

        assertThat("the true partition has ostrich",
                partitioned.get(true), containsInAnyOrder("ostrich"));
        assertThat(partitioned.get(true), hasSize(1));

        assertThat("the false partition has the resto of them",
                partitioned.get(false), containsInAnyOrder("duck", "falcon"));

        assertThat(partitioned.get(false), hasSize(2));
    }

    @Test
    public void partitioningByWithCollector() {

        final Map<Boolean, TreeSet<String>> partitioned =
                Stream.of("ostrich", "duck", "ferret", "falcon")
                        .collect(
                                Collectors.partitioningBy(
                                        s -> s.startsWith("f"),
                                        Collectors.toCollection(TreeSet::new)));


        assertThat("the true parition contains falcon, ferret in order",
                partitioned.get(true), contains("falcon", "ferret"));
        assertThat(partitioned.get(true), hasSize(2));

        assertThat("the false partition contains duck and ostrich in order",
                partitioned.get(false), contains("duck", "ostrich"));
        assertThat(partitioned.get(false), hasSize(2));

    }
}
