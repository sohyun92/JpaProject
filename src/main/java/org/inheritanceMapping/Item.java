package org.inheritanceMapping;

import javax.persistence.*;

@Entity

//1.조인 테이블 전략
@Inheritance(strategy = InheritanceType.JOINED)
/*
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
 2.단일 테이블 전략!
 DiscriminatorColumn 없어도 생김

 3.@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
  DiscriminatorColumn 필요없음
  단순하게 값넣고뺄떄는 좋음
  근데 테이블 다뒤져야함.. UNION으로 뒤짐..비효율적인 동작함

*/
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

