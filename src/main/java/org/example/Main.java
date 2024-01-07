package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin(); //데이터베이스 트랜잭션 시작
        try {
          // 저장
          //  Member member = new Member();
          //  member.setId(3L);
          //  member.setName("HelloC");
          //  entityManager.persist(member);//저장

            //삭제
            //Member findMember = entityManager.find(Member.class,1L);
            //entityManager.remove(findMember);

            //수정
            //findMember.setName("HelloJPA");

            /*
            JPQL란
            JPQL은 엔티티 객체를 대상으로 쿼리
            SQL은 데이터베이스 테이블 대상으로 쿼리
            테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
            한마디로 정의하면 객체 지향 SQL !
            */

            List<Member> result =entityManager.createQuery("select m from Member as m",Member.class)
                    .setFirstResult(15)
                    .setMaxResults(8)
                    .getResultList();

            for(Member member : result){
                System.out.println(member);
            }

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

        entityManager.close();

        emf.close();


    }
}