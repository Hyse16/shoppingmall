package study.shoppingmall.domain;

import lombok.*;
import study.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Item {

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

    // 재고수량 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
