package study.shoppingmall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import study.shoppingmall.domain.Order;
import study.shoppingmall.domain.QMember;
import study.shoppingmall.domain.QOrder;

import javax.persistence.EntityManager;
import java.util.List;

import static study.shoppingmall.domain.QMember.member;

public class OrderRepositoryImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        QOrder o = new QOrder("o");
        QMember m = new QMember("m");

        List<Order> result = queryFactory
                .selectFrom(o)
                .join(o.member, m)
                .where(o.status.eq(orderSearch.getOrderStatus()), m.name.eq(orderSearch.getMemberName()))
                .fetch();
        return  result;
    }
}
