package study.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.ItemImg;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
}
