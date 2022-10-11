package study.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
