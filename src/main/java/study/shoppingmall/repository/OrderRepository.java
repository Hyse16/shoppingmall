package study.shoppingmall.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
