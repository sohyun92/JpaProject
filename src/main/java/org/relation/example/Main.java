package org.relation.example;


import org.relation.example.domain.Member;
import org.relation.example.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        //연관관계가 필요한 잉유
        //- 객체지향 설계의 목표는 자율적인 객체들의 협력공통체를 만드는것이다.
        /*예제
        * 회원과 팀이있다.
        * 회원은 하나의 팀에만 소속될수있다.
        * 회원과 팀은 다대일 관계이다.*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
            //팀 저장
        /*    Team team = new Team();
            team.setName("TEAMA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("홍길동");
            member.setTeamId(team.getId());  //TeamId 몬가 객체지향스럽지 않다..
            entityManager.persist(member);
*/
            /* 객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력관계를 만들 수 없다.
            * -테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다.
            * -객체는 참조를 사용해서 연관된 객체를 찾는다.
            * */

            /*단방향 연관관계*/
         //저장
            Team team = new Team();
            team.setName("TEAMA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("홍길동");
            member.setTeam(team);
            entityManager.persist(member);

            Member fineMember = entityManager.find(Member.class,member.getId());
            Team findTeam = fineMember.getTeam();


            //새로운 팀B
            Team teamB = new Team();
            teamB.setName("TeamB");
            entityManager.persist(teamB);
            member.setTeam(teamB);



            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }


}
