package choi.web.repository;

import choi.web.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(Long memberId);

    Member findByName(String name);

}
