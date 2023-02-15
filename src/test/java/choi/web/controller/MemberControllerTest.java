package choi.web.controller;

import choi.web.domain.MEMBER_ROLES;
import choi.web.domain.Member;
import choi.web.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Test
    public void save() {
        Member member1 = Member.builder()
                .name("admin")
                .password("1")
                .roles(MEMBER_ROLES.ADMIN)
                .build();

        Long memberId = memberService.saveMember(member1);

        Assertions.assertEquals(member1.getMemberId(), memberId);
    }

    @Test
    public void findAll() {
        for (int i = 0; i < 10; i++) {
            Member member = Member.builder()
                    .name("member-" + i)
                    .password(String.valueOf(i))
                    .roles(MEMBER_ROLES.USER)
                    .build();

            memberService.saveMember(member);
        }

        List<Member> members = memberService.findAllMember();

        Assertions.assertEquals(10, members.size());
    }

    @Test
    public void findMember() {
        Member member1 = Member.builder()
                .name("member-1")
                .password("1")
                .roles(MEMBER_ROLES.ADMIN)
                .build();

        memberService.saveMember(member1);

        Member member2 = Member.builder()
                .name("member-2")
                .password("2")
                .roles(MEMBER_ROLES.ADMIN)
                .build();

        memberService.saveMember(member2);

        Member findMember = memberService.findMember(2L);

        Assertions.assertEquals("member-2", findMember.getName());
    }

}