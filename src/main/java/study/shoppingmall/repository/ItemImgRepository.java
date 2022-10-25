package study.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.shoppingmall.domain.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
}
