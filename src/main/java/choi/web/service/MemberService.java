package choi.web.service;

import choi.web.domain.Member;
import choi.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원 등록
     */
    public Long saveMember(Member member) {
        return memberRepository.save(member).getMemberId();
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Member findMember(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    /**
     * 회원 조회 (로그인용)
     */
    public Member findMemberByNameAndPassword(String name, String password) {
        return memberRepository.findByNameAndPassword(name, password);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(Long.parseLong(userId));
        List<String> authList = new ArrayList<>();
        authList.add(member.getRoles().name());

        return new User(
                String.valueOf(member.getMemberId()),
                member.getPassword(),
                Arrays.asList(member.getRoles().name()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
    }

}
