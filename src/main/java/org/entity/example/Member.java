package org.entity.example;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name ="Member") /*jpa가 관리하는 객체*/
public class Member {
  /*@Column
  * 1.name: 필드와 매핑할 테이블의 컬럼이름
  * 2.insertable, updatable : 등록, 변경 가능 여부
  * 3.nullable(DDL) null 값의 허용여부를 설정한다. false로 설정하면 DDL 생성시에 not null 제약조건이 붙는다
  * 4.unique(DDL) @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제약조건을 걸 때 사용
  * 5.columnDefinition : 데이터베이스의 컬럼 정보를 직접 줄 수 있다.
  * */

    @Id //pk 매핑
    private Long id;

    @Column(name="name",updatable = false ,columnDefinition = "varchar(100) default 'EMPTY'")   //db에는 name으로 쓸경우 컬럼매핑
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING) // EnumType : 기본이 ODINAL(enum의 순서를 데이터베이스에 저장 ,,, 사용XXX 위험함)
    // ,STRING (enum 이름을 데이터베이스에 저장)
    private RoleType roleType;


    @Temporal(TemporalType.TIMESTAMP) //날짜 타입 매핑 (자바 8에서 사용X LocalDate 사용함)
    private Date createdDate;

    private LocalDate createDate2;
    private LocalDateTime createDate3;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    @Transient  //디비랑 전혀 관계없이 메모리에서만 계산하고싶을떄
    private int temp;
    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getTemp() {
        return temp;
    }
}
