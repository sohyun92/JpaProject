package org.relation.example.domain;

import javax.persistence.*;

@Entity
public class Member2 {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    @Column(name ="USERNAME")
    private String username;
   /* @Column(name="TEAM_ID")
    private Long teamId;*/

    //연관관계맵핑
    @ManyToOne  //member N(many) 팀 1(one)
    @JoinColumn(name ="TEAM_ID")
    private Team2 team;

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

    public Team2 getTeam() {
        return team;
    }

    public void setTeam(Team2 team) {
        this.team = team;
    }

/*    public void changeTeam(Team2 team) {
        this.team = team;

        team.getMembers().add(this);
    }*/
}
