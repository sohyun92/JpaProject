package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            Member findMember = entityManager.find(Member.class,1L);
            //entityManager.remove(findMember);

            //수정
            findMember.setName("HelloJPA");

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