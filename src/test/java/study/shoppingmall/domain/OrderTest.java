package study.shoppingmall.domain;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.repository.ItemRepository;
import study.shoppingmall.repository.MemberRepository;
import study.shoppingmall.repository.OrderItemRepository;
import study.shoppingmall.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {

        return Item.builder()
                .name("테스트 상품")
                .price(10000)
                .itemDetail("테스트 상품 상세설명")
                .itemStatus(ItemStatus.SELL)
                .stockQuantity(100)
                .build();
    }

    @Test
    public void cascadeTest() {
        Order order = new Order();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();
            order.getOrderItems().add(orderItem);
        });

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }


    public Order createOrder() {
        Order order = new Order();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        });

        Member member = new Member();
        memberRepository.save(member);

        Order.builder()
                .member(member)
                .build();
        orderRepository.save(order);

        return order;
    }

    @Test
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }


    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    public void lazyLoadingTest() {
        Order order = this.createOrder();
        Long orderItemId= order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();


        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(EntityNotFoundException::new);
        System.out.println("orderclass = " + orderItem.getOrder().getClass());
    }
}