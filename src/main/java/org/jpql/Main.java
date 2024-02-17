package org.jpql;



import javax.persistence.*;
import java.util.List;

public class Main {
   public static void main(String[] args) {
        jpqlExam();
    }

    public static void jpqlExam() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            entityManager.persist(member);

            
            /*TypedQuery : 반환 타입이 명활할 때 사용 
            * Query : 반환 타입이 명확하지 않을 때 사용*/
            TypedQuery<Member> query= entityManager.createQuery("select m from Member m where m.username= :username", Member.class);
            query.setParameter("username","member1");
            //TypedQuery<String> query2= entityManager.createQuery("select m.username from Member m", String.class);
            //Query query3 = entityManager.createQuery("select m.username, m.age from Member m");

            /*getResultList : 결과가 하나 이상일 떄, 리스트 반환 
            *                  -결과가 없으면 빈 리스트 반환(null 익셉션 걱정할필요없음)
            * getSingleResult : 결과가 정확히 하나, 단일 객체 반환
            *                  -결과 안나오거나 두개이상일경우 익셉션.. */

            List<Member> result = query.getResultList();
            //query.getSingleResult();




            tx.commit();



        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }



}