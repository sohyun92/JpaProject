package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {


    @Id
    private Long id;
    private String name;

    public Member(){

    }
    public Long getId() {
        return id;
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
