package jpabook.model.entity;

import jpabook.model.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item; // 주문 상품

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order; // 주문
    
    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
    
}
