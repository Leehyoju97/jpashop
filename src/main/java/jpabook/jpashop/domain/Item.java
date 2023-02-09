package jpabook.jpashop.domain;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("상품 재고가 부족하다.");
        }
    }

}
