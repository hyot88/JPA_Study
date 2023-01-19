package jpabook.start.chap5;

import jpabook.model.OrderStatus;
import jpabook.model.entity.item.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // JPA에 관련된 요소들만 테스트하기 위한 어노테이션으로 JPA 테스트에 관련된 설정들만 적용해준다.
/**
 * @DataJpaTest로 테스트하면 @Transactional 어노테이션이 기본으로 적용되어 있어서 무조건 롤백이 되므로,
 * 롤백이 안되게 false 처리 해준다.
 */
@Rollback(false)
/**
 * AutoConfigureTestDatabase.Replace.ANY는 임베디드 DB를 바라보고,
 * AutoConfigureTestDatabase.Replace.NONE은 설정한 값에 따라 DB 소스가 적용된다.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class) // JUnit5 를 사용하기 위해 추가한다.
public class JpaTest {

    @Autowired
    private TestEntityManager testEntityManager; // 테스트에서 사용되는 EntityManager

    @Test
    public void 테스트() {
        // Member 추가
        /*Member member = new Member();
        member.setName("안효성");
        member.setCity("인천");
        member.setStreet("부평구");
        member.setZipcode("545");
        testEntityManager.persist(member);
        Long memberId = member.getId();

        // Order 추가
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.ORDER);
        order.setMember(member);
        testEntityManager.persist(order);
        Long orderId = order.getId();

        // Item 추가
        Item item1 = new Item();
        item1.setName("닌텐도");
        item1.setPrice(1000);
        item1.setStockQuantity(1);
        testEntityManager.persist(item1);
        Long item1Id = item1.getId();

        Item item2 = new Item();
        item2.setName("스위치");
        item2.setPrice(2000);
        item2.setStockQuantity(2);
        testEntityManager.persist(item2);
        Long item2Id = item2.getId();

        // OrderItem 추가
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setOrder(order);
        orderItem1.setItem(item1);
        orderItem1.setOrderPrice(2000);
        orderItem1.setCount(2);
        order.addOrderItem(orderItem1);
        testEntityManager.persist(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setOrder(order);
        orderItem2.setItem(item2);
        orderItem2.setOrderPrice(4000);
        orderItem2.setCount(2);
        order.addOrderItem(orderItem2);
        testEntityManager.persist(orderItem2);

        // 객체 그래프 탐색
        // 1. 주문한 회원 검색
        Order searchOrder = testEntityManager.find(Order.class, orderId);
        assertThat(searchOrder.getId()).isEqualTo(orderId); // id 체크
        Member searchMember = searchOrder.getMember();
        assertThat(searchMember.getId()).isEqualTo(memberId); // id 체크

        // 2. 주문한 상품 검색
        List<OrderItem> searchOrderItemList = searchOrder.getOrderItems();
        assertThat(searchOrderItemList).hasSize(2); // 주문 아이템 개수 체크
        Item searchItem1 = searchOrderItemList.get(0).getItem();
        assertThat(searchItem1.getId()).isEqualTo(item1Id); // id 체크
        Item searchItem2 = searchOrderItemList.get(1).getItem();
        assertThat(searchItem2.getId()).isEqualTo(item2Id); // id 체크*/
    }

    @Test
    public void 테스트_롤백() {
        // 실행코드는 없지만, ddl-auto create 값 덕분에 모든 테이블을 drop하고 create하여 초기화한다.
    }
}
