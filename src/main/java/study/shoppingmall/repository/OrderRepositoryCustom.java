package study.shoppingmall.repository;

import study.shoppingmall.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    public List<Order> findAll(OrderSearch orderSearch);

}
