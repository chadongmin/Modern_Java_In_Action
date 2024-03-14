import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest2 {

    private List<Integer> list = Arrays.asList(1, 7, 3, 2, 9, 0, 8, 9);

    @Test
    void filter_test() {
        list.stream().filter(i -> i > 5).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    void sort_test() {
        list.stream().sorted((o1, o2) -> o1-o2).collect(Collectors.toList()).forEach(System.out::println
        );
    }
}
