package org.relation.example;


import org.relation.example.domain.Member;
import org.relation.example.domain.Member2;
import org.relation.example.domain.Team;
import org.relation.example.domain.Team2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
             //oneRelationExam(tx,entityManager);  단방향 연관관계
            twowayRelayionExam(tx,entityManager); //양방향 연관관계

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

    private static void twowayRelayionExam(EntityTransaction tx, EntityManager entityManager) {
        //양방향 연관관계
        //테이블 연관관계에는 상관없고 객체문제
        //팀에 members list 를 넣어줘야 양방향 가능한부분..

        /*연관관계의 주인 
        * -객체의 두 관계중 하나를 연관관계의 주인으로 지정s
        * 주인이 아닌쪽은 읽기만가능!!
        * 주인은 mappedBy 속성을 사용하면 안됨
        * 주인이 아니면 mappedBy 속성으로 주인 지정
        * 누구를 주인으로 해야하나? 외래키가 있는 쪽을 주인으로!!!!!!!!*/

        Team2 team = new Team2();
        team.setName("TEAMA");

        //연관관계 주인인 곳에 값을 넣어야한다.
        //양방향 매핑시에 무한 루프를 조심하자
        //예 ) toString(), lombok, JSON 생성라이브러리..

        //양방향 매핑정리
        //단방향 매핑만으로도 이미 연관관계 매핑은 완료..
        //첨에 단방향 매핑을 잘하고 양방향은 필요할 떄 추가해도됨.(테이블에 영향을 주지않음)
       Member2 member =new Member2();
       member.setUsername("member1");
       entityManager.persist(member);
       team.addMember(member);
       entityManager.persist(team);

       entityManager.flush();
       entityManager.clear();


       Member2 findMember = entityManager.find(Member2.class,member.getId());
       List<Member2> members =findMember.getTeam().getMembers();

       for(Member2 m : members) {
           System.out.println("m="+m);
       }



    }

    private static void oneRelationExam(EntityTransaction tx, EntityManager entityManager) {

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


    }

}
