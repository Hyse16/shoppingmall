package study.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import study.shoppingmall.exception.NotEnoughStockException;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    @Enumerated
    private ItemSizeStatus itemSizeStatus;

    // 재고수량 증가
    public void addStock(int quantity) {
        this.stockQuantity += (quantity);
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
