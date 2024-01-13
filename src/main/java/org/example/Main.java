package org.example;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
   public static void main(String[] args) {
        flushExam();
    }

    public static void flushExam() {
       
        //플러시 : 영속성 컨텍스트의 변경내용을 데이터베이스에 반영 (영속성 컨텍스트를 비우지 않음)
        // 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화

        //플러시 발생
        //1.-변경감지
        //2.-수정된 엔티티 쓰기 지연 SQL 저장소에 등록
        //3.-쓰기지연 SQL 저장소의 쿼리를 데이터베이스에 전송(등록,수정,삭제 쿼리)

        //플러시 하는 방법
        //em.flush -- (직접호출)
        //트랜잭션 커밋 -- 자동 호출
        //JPQL 쿼리 실행 -- 자동호출`

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
            Member member =new Member(200L,"member200");
            entityManager.persist(member);

            entityManager.flush();  // insert 가 즉시 나감
            System.out.println("================");
            tx.commit(); // 그리구 commit!

            /*
            *  플러시 모드 옵션
            * em.setFlushMode(FlushModeType.COMMIT)
            * FlushModeType.AUTO : 기본값
            * FLushModeType.COMMIT : 커밋을 할 때만 FLUSH
            *
            * */


        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

    public static void study2Update() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        try{
            //수정(변경감지)

            Member member = entityManager.find(Member.class, 150L);
            member.setName("ZZZZ");

            //em.persist(member) <--- 이런거 있어야 update 되지않을까? ---> 쓰지않아도 데이터가 변경됨

            System.out.println("================");

            tx.commit(); // [트랜잭션 커밋]

         }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
    }

    }

    public static void study2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin(); //데이터베이스 트랜잭션 시작

        try{
            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"A");

            entityManager.persist(member1);
            entityManager.persist(member2);
            System.out.println("----------------");
            //여기까지 INSERT SQL 을 데이터에 보내지않는다.

            tx.commit(); //커밋하는 순간 데이터 베이스에 insert SQL을 보낸다.
            /* persistence.xml 한번에 추가-->  <property name="hibernate.jdbc.batch.size" value="10"/>
             */

        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }
    }


}