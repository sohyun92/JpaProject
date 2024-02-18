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

    public static void projectionExam() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
            /* 프로젝션
             * - select 절에 조회할 대상을 지정하는것 */

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            /*TypedQuery : 반환 타입이 명활할 때 사용
             * Query : 반환 타입이 명확하지 않을 때 사용*/
            List<Member> query= entityManager.createQuery("select t from Member m join m.team t", Member.class).getResultList();
            //여러값 조회
            //new 명령어로 조회
            //단순 값을 DTO 로 바로조회
            List<MemberDTO> result = entityManager.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();

            //조인 -on절
            // 1. 조인 대상 필터링
            // 2. 연관관계 없는 엔티티 외부 조인

            /*예 ) 회원과 팀을 조인하면서 팀이름이 A인 팀만 조인
            * 1.JPQL
            * SELECT m, t FROM Member m LEFT JOIN m.team t on t.name ='A'
            * 2.SQL
            * SELECT m.*, t.* FROM MEMBER m LEFT JOIN TEAM t ON m.TEAM_ID =
            * t.id and t.name ='A'*/
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }
    public static void PagingExam() {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            /*페이징
             * setFirstResult : 조회 시작 위치
             * setMaxReulsts : 조회 끝 위치 */
            entityManager.createQuery("select m from Member m order by m.age desc",Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }
    }

    public static void joinExam() {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
            /*1.내부조인
              SELECT m FROM MEMBER m [INNER] JOIN m.team t
            * 2.외부조인
              SELECT m FROM MEMBER m LEFT [OUTER] JOIN m.team t
            * 3.세타조인
              SELECT count(m) FROM MEMBER m, TEAM t WHERE m.username = t.name
            */


            String query = "select m from Member m inner join m.team t";
            List<Member> result = entityManager.createQuery(query, Member.class)
                            .getResultList();
            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }
    }
}