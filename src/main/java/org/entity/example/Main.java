package org.entity.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main
{
    public static void main(String[] args) {
        /*
        * 엔티티 매핑소개
        *
        * 1.객체와 테이블 매핑 @Entity, @Table
        * 2.필드와 컬럼 매핑 @Column
        * 3.기본 키 매핑 @Id
        * 4.연관관계 매핑 @ManyToOne, @JoinColumn
        *
        * */

        //객체와 테이블 매핑
        //@Entity가 붙은 클래스는 JPA가 관리, 엔티티라함
        //주의 * 기본생성자 필수
        //주의 * final 클래스 , enum, interface, inner 클래스 사용 X
        //주의 * 저장할 필드에 final 사용 X

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setId(2L);
            member.setUsername("B");
            member.setRoleType(RoleType.ADMIN);
            entityManager.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

}
