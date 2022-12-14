package jpabook.start.chap2;

import jpabook.start.Member_Temp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // JPA에 관련된 요소들만 테스트하기 위한 어노테이션으로 JPA 테스트에 관련된 설정들만 적용해준다.
/**
 * @DataJpaTest로 테스트하면 @Transactional 어노테이션이 기본으로 적용되어 있어서 무조건 롤백이 되므로,
 * 롤백이 안되게 false 처리 해준다.
 */
@Rollback(false)
/**
 * AutoConfigureTestDatabase.Replace.ANY는 임베디드 DB를 바라보고,
 * AutoConfigureTestDatabase.Replace.NONE은 설정한 값에 따라 DB 소스가 적용된다.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class) // JUnit5 를 사용하기 위해 추가한다.
public class JpaTest {

    @Autowired
    private TestEntityManager testEntityManager; // 테스트에서 사용되는 EntityManager

    @Test
    public void 테스트() {
        String id = "id1";
        String userName = "지한";
        int age = 2;
        Member_Temp memberTemp = new Member_Temp();
        memberTemp.setId(id);
        memberTemp.setUsername(userName);
        memberTemp.setAge(age);

        // 등록
        testEntityManager.persist(memberTemp);

        // 수정
        age = 20;
        memberTemp.setAge(age);

        // 한 건 조회
        Member_Temp findMemberTemp = testEntityManager.find(Member_Temp.class, id);

        /**
         * 하나 이상의 회원 목록을 조회
         * 여러 데이터를 조회하려면 검색 조건이 포함된 SQL을 사용해아 한다.
         * JPA는 JPQL(Java Persistence Query Language) 이라는, SQL을 추상화한 객체지향 쿼리 언어로 이런 문제를 해결한다.
         * JPQL은 SQL과 문법이 거의 유사하다.
         * JPQL은 엔티티 객체를 대상으로 쿼리한다. 쉽게 이야기해서 클래스와 필드를 대상으로 쿼리한다.
         * SQL은 데이터베이스 테이블을 대상으로 쿼리한다.
         */
        // testEntityManager는 createQuery() 가 없어서 getEntityManager() 로 EntityManager를 가져온다.
        EntityManager entityManager = testEntityManager.getEntityManager();
        // 해당 쿼리가 JPQL이다. 참고로 여기서 Member는 엔티티 객체를 말한다. MEMBER 테이블이 아니다. JPQL은 데이터베이스를 전혀 알지 못한다.
        TypedQuery<Member_Temp> query = entityManager.createQuery("select m from Member_Temp m", Member_Temp.class);
        List<Member_Temp> memberTemps = query.getResultList();

        // 조회한 값 검증
        assertThat(findMemberTemp.getId()).isEqualTo(id); // id 체크
        assertThat(findMemberTemp.getUsername()).isEqualTo(userName); // userName 체크
        assertThat(findMemberTemp.getAge()).isEqualTo(age); // age 체크
        assertThat(memberTemps).hasSize(1); // 조회한 list size 체크
    }

    @Test
    public void 테스트_롤백() {
        // 실행코드는 없지만, ddl-auto create 값 덕분에 모든 테이블을 drop하고 create하여 초기화한다.
    }
}
