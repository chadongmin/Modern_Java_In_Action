import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class PredicateInterfaceTest {

    public <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Test
    public void Predicate_인터페이스_테스트() {

        PredicateInterfaceTest pt = new PredicateInterfaceTest();
        List<String> filtered = pt.filter(Arrays.asList("chadongmin", "sadfa", "asdf"),
            (String s) -> s.contains("chadongmin"));

        assertThat(filtered.get(0)).isEqualTo("chadongmin");
        //chadongmin이 출력됨

        List<Dish> dishes = pt.filter(List.of(
            new Dish("chicken", 1000),
            new Dish("salad", 300),
            new Dish("noodle", 700)), (dish -> dish.getCalories() < 500));

        assertThat(dishes.size()).isEqualTo(1);
        assertThat(dishes.get(0).getName()).isEqualTo("salad");


    }
}
