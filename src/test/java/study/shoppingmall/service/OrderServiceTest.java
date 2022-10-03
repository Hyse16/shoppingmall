package study.shoppingmall.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.domain.*;
import study.shoppingmall.repository.OrderRepository;
import study.shoppingmall.repository.OrderSearch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
        em.persist(item);

        int orderCount = 2;
        String orderSize = "M";

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount, orderSize);

        Order getOrder = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);

        assertEquals(OrderStatus.ORDER,getOrder.getStatus());

    }

    @Test
    public void 주문취소() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Item item = new Item();
        item.setName("afds");
        item.setStockQuantity(10);
        item.setPrice(10000);
        em.persist(item);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount,"S");
//When
        orderService.cancelOrder(orderId);
        //Then
        Order getOrder = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals( 10, item.getStockQuantity());
    }

    @Test
    public void 재고수량초과() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Item item = new Item();
        item.setName("afds");
        item.setStockQuantity(10);
        item.setPrice(10000);
        em.persist(item);

        int orderCount = 10; //재고보다 많은 수량 //When
        orderService.order(member.getId(), item.getId(), orderCount,"S");
        //Then
    }

    @Test
    public void searchTest() {
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member1.setName("member2");

        em.persist(member1);
        em.persist(member2);


        Order order1 = new Order();
        order1.setMember(member1);
        order1.setStatus(OrderStatus.CANCEL);

        Order order2 = new Order();
        order2.setMember(member2);
        order2.setStatus(OrderStatus.ORDER);

        em.persist(order1);
        em.persist(order2);

        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setOrderStatus(OrderStatus.CANCEL);
        orderSearch.setMemberName("member1");

        List<Order> result = orderRepository.findAll(orderSearch);

        assertThat(result).extracting("MemberName").containsExactly("member1");

    }}