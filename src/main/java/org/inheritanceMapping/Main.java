package org.inheritanceMapping;

import org.entity.example.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main
{
    public static void main(String[] args) {

        
        /*
        *상속관계 매핑
        *
        * 객체의 상속과 구조와 DB의 슈퍼타입 서브타입관계를 매핑
        * 주요 어노테이션
        * @Inheritance
        *
        * 조인전략의 장점
        * 1.테이블의 정규화
        * 2.외래 키 참조 무결성 제약조건 활용가능
        * 3.저장공간 효율성
        *
        * 단점
        * 1.조회시 조인을 많이 사용, 성능저하
        * 2.조회쿼리 복잡함
        * 3.데이터 저장시 INSERT SQL 2번 호출*/

        /*단일 테이블 저장
        * 장점
        * 1.조인이 필요없으므로 일반적으로 조회 성능이 빠름
        * 2.조회 쿼리 단순
        * 단점
        * 1.자식 엔티티가 매핑한 컬럼은 모두 null 허용
        * 2.단일 테이블에 모든것을 저장하므로 테이블이 커질수있고 상황에따라 조회성능이
        * 오히려 느려질수있따.*/

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try{
           Movie movie = new Movie();
            movie.setDirector("a");
            movie.setActor("bbb");
            movie.setName("바람과함께사라지다.");
            movie.setPrice(10000);
            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            Movie findMovie = entityManager.find(Movie.class,movie.getId());
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            entityManager.close();
        }

    }

}
