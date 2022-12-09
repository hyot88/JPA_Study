package jpabook.start.chap4;

import jpabook.start.Board;
import jpabook.start.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Board board = new Board();
        testEntityManager.persist(board);

        String id = "id1";
        String userName = "지한";
        int age = 2;
        Member member = new Member();
        member.setId(id);
        member.setUsername(userName);
        member.setAge(age);
        member.setFirstName("효성");
        member.setLastName("안");

        // 등록
        testEntityManager.persist(member);
    }

    @Test
    public void 테스트_롤백() {
        Board findBoard = testEntityManager.find(Board.class, "id1");
        testEntityManager.remove(findBoard);
    }
}
