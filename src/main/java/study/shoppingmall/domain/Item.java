package study.shoppingmall.domain;

import lombok.*;
import study.shoppingmall.dto.ItemFormDto;
import study.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Item extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    @Column(nullable = false)
    private int price;

    private int stockQuantity;


    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated
    private ItemStatus itemStatus;

    @Builder
    public Item(String name, int price, int stockQuantity, String itemDetail, ItemStatus itemStatus) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDetail = itemDetail;
        this.itemStatus = itemStatus;
    }

    public void updateItem(ItemFormDto itemFormDto) {
        this.name = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockQuantity = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemStatus = itemFormDto.getItemStatus();
    }

}


