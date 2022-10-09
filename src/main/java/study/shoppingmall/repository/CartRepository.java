package study.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
