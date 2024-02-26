import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamTest {

    List<Dish> menu = Arrays.asList(
            new Dish("hamburger",500),
            new Dish("chicken",1000),
            new Dish("sandwich", 200));

    @Test
    public void 스트림_사용하지않고_for문과_익명클래스를_사용하는_경우(){
        //for loop
        List<Dish> lowCaloriesDish = new ArrayList<>();
        for (Dish dish : menu){
            if (dish.getCalories() < 2000){
                lowCaloriesDish.add(dish);
            }
        }
        assertEquals(lowCaloriesDish.get(0).getCalories(), 500);
        // 리스트에 가장 먼저 생성된 햄버거 인스턴스가 500이므로 첫번째 원소에 500이 담긴다
        lowCaloriesDish.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });
        assertEquals(lowCaloriesDish.get(0).getCalories(), 200);
        //정렬 이후에는 오름차순 정렬이기 때문에 200이다.
    }

    @Test
    public void 스트림을_사용하는_경우(){
        List<Dish> lowCaloriesDish = new ArrayList<>();
        lowCaloriesDish = menu.stream()
                .filter(dish -> dish.getCalories() < 2000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .collect(Collectors.toList());

        assertEquals(lowCaloriesDish.get(0).getName(), "sandwich");
    }
}