package study.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.Item;

public interface ItemRepository extends JpaRepository<Item,Long>,ItemRepositoryCustom {
}
