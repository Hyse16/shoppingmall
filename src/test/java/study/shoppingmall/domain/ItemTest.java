package study.shoppingmall.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.repository.ItemRepository;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void createItem() {
        Item item = Item.builder()
                .name("test")
                .price(1000)
                .itemDetail("테스트상품")
                .itemStatus(ItemStatus.SELL)
                .stockQuantity(100)
                .build();
        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem.toString());
    }

}