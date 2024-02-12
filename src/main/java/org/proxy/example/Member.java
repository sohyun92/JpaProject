package org.proxy.example;

import org.relation.example.domain.Team;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    @Column(name ="USERNAME")
    private String username;
   /* @Column(name="TEAM_ID")
    private Long teamId;*/

    //연관관계맵핑
    //@ManyToOne(fetch = FetchType.LAZY)  //지연 로딩 LAZY를 사용해서 프록시로 조회 // member N(many) 팀 1(one)
    @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩(Member와 Team을 함께 조회)
    @JoinColumn(name ="TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
