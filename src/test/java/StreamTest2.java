import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Order;
import model.OrderLine;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StreamTest2 {

    private List<Integer> list = Arrays.asList(1, 7, 3, 2, 9, 0, 8, 9);

    @Test
    void filter_test() {
        list.stream()
            .filter(i -> i > 5)
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }

    @Test
    void sort_test() {
        list.stream().sorted((o1, o2) -> o1 - o2).collect(Collectors.toList())
            .forEach(System.out::println
            );
    }

    @Test
    void IntStream_test() {
        IntStream.builder()
            .add(1)
            .add(2)
            .build()
            .forEach(System.out::println);

        System.out.println("=====");

        IntStream.of(1, 2, 3).forEach(System.out::println);

        System.out.println("=====");

        IntStream.generate(() -> 5).limit(3).forEach(System.out::println);

        System.out.println("=====");

        IntStream.range(7, 9).forEach(System.out::println);

        System.out.println("=====");

        IntStream.rangeClosed(7, 9).forEach(System.out::println);
    }

    @Test
    void IntStream_Compare_Stream() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        IntStream.range(1, 1000).boxed().count();
        stopWatch.stop();

        System.out.println("원시 스트림 사용시 Boxing이 추가 된 시간 " + stopWatch.getNanoTime());

        stopWatch.reset();

        stopWatch.start();
        IntStream.range(1, 1000).count();
        stopWatch.stop();

        System.out.println("IntStream 사용시 Boxing이 없는 시간" + stopWatch.getNanoTime());

        IntStream.of(9, 65, 2, 35, 1, 2, 3, 4, 5)
            .sorted()
            .forEach(System.out::println);

    }

    @Test
    void flatMap_test() {
        String[][] cities = new String[][]{
            {"Seoul", "Busan"},
            {"San Francisco", "New York"},
            {"Madrid", "Barcelona"}
        };

        //FlatMap을 사용하지 않고, 2차원 배열을 바로 toList로 변환하게 되면 List안에 배열이 들어간다.
        List<String[]> nonFlayMapList = Arrays.stream(cities).collect(Collectors.toList());
        List<String> flatMapList = Arrays.stream(cities).flatMap(Arrays::stream)
            .collect(Collectors.toList());

        System.out.println(flatMapList);

        Assertions.assertEquals(flatMapList.size(),
            Arrays.stream(cities).flatMap(c -> Arrays.stream(c)).count());
    }

    @Test
    void flatMap_test2() {
        Order order1 = new Order()
            .setId(1001)
            .setOrderLines(Arrays.asList(
                new OrderLine()
                    .setId(10001)
                    .setType(OrderLine.OrderLineType.PURCHASE)
                    .setAmount(BigDecimal.valueOf(5000)),
                new OrderLine()
                    .setId(10002)
                    .setType(OrderLine.OrderLineType.PURCHASE)
                    .setAmount(BigDecimal.valueOf(4000))
            ));
        Order order2 = new Order()
            .setId(1002)
            .setOrderLines(Arrays.asList(
                new OrderLine()
                    .setId(10003)
                    .setType(OrderLine.OrderLineType.PURCHASE)
                    .setAmount(BigDecimal.valueOf(2000)),
                new OrderLine()
                    .setId(10004)
                    .setType(OrderLine.OrderLineType.DISCOUNT)
                    .setAmount(BigDecimal.valueOf(-1000))
            ));
        Order order3 = new Order()
            .setId(1003)
            .setOrderLines(Arrays.asList(
                new OrderLine()
                    .setId(10005)
                    .setType(OrderLine.OrderLineType.PURCHASE)
                    .setAmount(BigDecimal.valueOf(2000))
            ));
        List<Order> orders = Arrays.asList(order1, order2, order3);

        //FlatMap을 사용하지 않아서 List안에 List가 포함된 채로 반환됨.
        List<List<OrderLine>> nonFlatMapList = orders.stream().
            map(order -> order.getOrderLines())
            .collect(Collectors.toList());

        List<OrderLine> flatMapStream = orders.stream()
            .map(Order::getOrderLines)
            .flatMap(order -> order.stream())
            .collect(Collectors.toList());

        //map -> flatMap이 아닌, flatMap 한번으로 가능함
        List<OrderLine> flatMapStream2 = orders.stream()
            .flatMap(order -> order.getOrderLines().stream())
            .collect(Collectors.toList());

        System.out.println(flatMapStream);
        System.out.println(flatMapStream2);

        Assertions.assertEquals(flatMapStream, flatMapStream2);
    }
}
