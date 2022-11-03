package study.shoppingmall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.shoppingmall.domain.Item;
import study.shoppingmall.dto.ItemSearchDto;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
