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


        /*
        *
        * 기본키 매핑 어노테이션
        * @id , @GeneratedValue
        * 기본키 제약조건 : null 아님, 유일, 변하면 안됨
        * 자연키는 찾기 어렵고 대리키(대체키)를 사용하자
        * 예를들면 주민등록번호도 기본키로 적합하지않다..
        * 권장: Long형 + 대체키 + 키 생성전략 사용
        * */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            //member.setId("ID_C");
            // @GeneratedValue(strategy = GenerationType.IDENTITY)
            //IDENTITY 전략에서만 예외적으로 commit 전에 db에 insert 쿼리 날림 (보통은 commit 시점이지만)
            entityManager.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

}
