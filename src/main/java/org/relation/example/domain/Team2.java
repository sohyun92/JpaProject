package org.relation.example.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team2 {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") //양방향 !!! member2의 team에 매핑@@
    //mappedBy
    /*객체와 테이블이 관계를 맺는 차이 
    * 객체연관관계 = 2개 
    * 1.회원->팀(단방향)
    * 2.팀->회원(단방향)
    * 테이블연관관계
    * 1.회원<->팀 연관관계 1개 (양방향)
    * 객체의 양방향관계는 사실 양방향관계가 아니라 서로 다른 단방향 관계가 2개다
    * 객체를 양방향으로 참조하려면 단방향 연관관계 2개가 필요
    * 테이블을 양방향으로 참조하려면 외래키 !!*/
    private List<Member2> members= new ArrayList<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member2> getMembers() {
        return members;
    }

    public void setMembers(List<Member2> members) {
        this.members = members;
    }

    public void addMember(Member2 member) {
        member.setTeam(this);
        members.add(member);

    }
}
