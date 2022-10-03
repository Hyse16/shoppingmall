package study.shoppingmall.repository;

import lombok.Getter;
import lombok.Setter;
import study.shoppingmall.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
