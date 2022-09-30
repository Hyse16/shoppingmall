package study.shoppingmall.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.domain.*;
import study.shoppingmall.repository.ItemRepository;
import study.shoppingmall.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {


    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("부산", "신도시로", "42303"));
        em.persist(member);

        Item item = new Item();
        item.setName("가디건");
        item.setPrice(10000);
        item.setStockQuantity(10);

        int orderCount = 2;
        String orderSize = "M";

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount, orderSize);

        Order getOrder = orderRepository.findOrderById(orderId);

        assertEquals(OrderStatus.ORDER,getOrder.getStatus());

    }

    @Test
    public void 주문취소() throws Exception {

    }

    @Test
    public void 재고수량초과() throws Exception {

    }
}