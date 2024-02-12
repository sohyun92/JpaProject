package org.proxy.example;


import org.relation.example.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
   public static void main(String[] args) {
        ploxyExam();
    }

    public static void ploxyExam() {

        /*프록시 기초
        * -em.find() vs em.getReference()
        * em.fine : 데이터베이스를 통해서 실제 엔티티 객체 조회
        * em.getReference() : 데이터 베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
        * 실제 클래스를 상속받아서 만들어짐. 실제 클래스와 겉모양이 같다
        * 사용하는 입장에서는 진짜 객체인지 프록시 객체인지 구분하지 않고 사용하면 됨
        * 프록시 객체는 실제 객체의 참조를 보관한다.
        * 객체를 호출하면 프록시 객체는 실제 객체의 메소드 호출*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{

            Member member = new Member();
            member.setUsername("hello");
            entityManager.persist(member);

            Team team = new Team();
            team.setName("team1");
            entityManager.persist(team);

            entityManager.flush();
            entityManager.clear();
            
            //1.findMember team이랑 member랑 다 조회해온다..
            //Member findMember = entityManager.find(Member.class,member.getId());
            //2.
            Member m = entityManager.getReference(Member.class,member.getId());

            m.getTeam().getName(); //초기화
            tx.commit(); // 그리구 commit!
            /*지연로딩, 즉시로딩 주의
            * 가급적 지연로딩만 사용(특히 실무에서)
            * 즉시 로딩을 적용하면 예상치 못한 SQL이 발생하며
            * JPQL에서 N+1문제를 일으킨다.
            * @ManyToOne @OneToOne은 기본이 즉시로딩, ------>LAZY로 설정
            * @OneToMany @ManyToMany는 기본이 지연로딩*/


            /*영속성 전이 : CASCADE
            * 특정엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만들고싶을떄*/
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

    private static void printMemberAndTeam(Member member) {
       //멤버랑 팀 출력
        //어떤경우에는 멤버만 출력하고싶을수도있음..
       String username = member.getUsername();
       System.out.println(username);
       Team team = member.getTeam();
       System.out.println(team);

    }


}