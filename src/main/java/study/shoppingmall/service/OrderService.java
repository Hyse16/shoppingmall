package study.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.shoppingmall.domain.*;
import study.shoppingmall.repository.*;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;



    @Transactional
    public Long order(Long memberId, Long itemId, int count, String size) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalAccessError::new);
        Item item = itemRepository.findById(itemId).orElseThrow(IllegalAccessError::new);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count, size);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }


    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        order.cancel();

    }
}
