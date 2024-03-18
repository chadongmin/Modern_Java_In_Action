import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Order;
import model.Order.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PartitionBy {

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
		.setStatus(OrderStatus.ERROR);
	List<Order> orders = Arrays.asList(order1, order2, order3, order4);

	@Test
	void partitionByPractice(){

		Map<Boolean, List<Order>> nonErrorStatusListMap = orders.stream().collect(
			Collectors.partitioningBy(order -> !order.getStatus().equals(OrderStatus.ERROR)));

		// partitioningBy는 파라미터로 Predicate을 넘겨서, 조건 만족 여부에 따른 boolean을 key값으로 하는 Map을 리턴

		// status가 ERROR인 주문의 개수는 1개이므로 true를 key로 가지는 map의 value는 1개뿐
		Assertions.assertEquals(nonErrorStatusListMap.get(true).size(),1);

		Assertions.assertEquals(nonErrorStatusListMap.get(false).size(),3);
	}
}
