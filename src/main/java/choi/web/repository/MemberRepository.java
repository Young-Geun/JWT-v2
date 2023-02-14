package choi.web.repository;

import choi.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(Long memberId);

    Optional<Member> findByNameAndPassword(String name, String password);

}
