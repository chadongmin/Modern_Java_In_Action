import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Order;
import model.Order.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToMap {

	@Test
	void toMapPractice() {
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
		List<Order> orders = Arrays.asList(order1, order2, order3, order4);

		//TODO : orderID를 Key로 갖고, Status를 Value로 갖는 Map으로 바꾸기
		Map<Long, OrderStatus> orderStatusMap = orders.stream()
			.collect(Collectors.toMap(Order::getId, Order::getStatus));

		Assertions.assertEquals(orderStatusMap.get(1001L), OrderStatus.CREATED);
	}
}
