import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Order;
import model.Order.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupingBy {
	Order order1 = new Order()
		.setId(1001L)
		.setAmount(BigDecimal.valueOf(2000))
		.setStatus(OrderStatus.CREATED);
	Order order2 = new Order()
		.setId(1002L)
		.setAmount(BigDecimal.valueOf(4000))
		.setStatus(OrderStatus.ERROR);
	Order order3 = new Order()
		.setId(1003L)
		.setAmount(BigDecimal.valueOf(3000))
		.setStatus(OrderStatus.ERROR);
	Order order4 = new Order()
		.setId(1004L)
		.setAmount(BigDecimal.valueOf(7000))
		.setStatus(OrderStatus.PROCESSED);

	@Test
	void groupingByPractice(){

		List<Order> orders = Arrays.asList(order1, order2, order3, order4);

		Map<OrderStatus, List<Order>> orderStatusListMap = orders.stream()
			.collect(groupingBy(x -> x.getStatus()));

		System.out.println(orderStatusListMap);

		//OrderStatus가 ERROR인 List는 order2, order3으로 총 2개이다.
		Assertions.assertEquals(orderStatusListMap.get(OrderStatus.ERROR).size(), 2);
	}

	@Test
	void groupingByPractice2(){

		List<Order> orders = Arrays.asList(order1, order2, order3, order4);

		Map<OrderStatus, BigDecimal> addResultMap = orders.stream()
			.collect(groupingBy(x -> x.getStatus(),
				Collectors.mapping(x -> x.getAmount(),
					reducing(BigDecimal.ZERO, BigDecimal::add))));

		System.out.println(addResultMap);
	}

}
