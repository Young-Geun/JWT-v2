package choi.web.controller.v1;

import choi.web.domain.Member;
import choi.web.jwt.JwtTokenProvider;
import choi.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    // TODO: 2/14/23 - 응답 값 정규화

    /**
     * 회원 등록
     */
    @PostMapping("/v1/members")
    public ResponseEntity saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return ResponseEntity
                .ok()
                .body(member);
    }

    /**
     * 회원 전체 조회
     */
    @GetMapping("/v1/members")
    public ResponseEntity findMember() {
        return ResponseEntity
                .ok()
                .body(memberService.findAllMember());
    }

    /**
     * 회원 조회
     */
    @GetMapping("/v1/members/{memberId}")
    public ResponseEntity findMember(@PathVariable("memberId") Long id) {
        return ResponseEntity
                .ok()
                .body(memberService.findMember(id));
    }

    /**
     * 로그인
     */
    @PostMapping("/v1/login")
    public ResponseEntity login(@RequestBody Member member) {
        Member findMember = memberService.findMemberByNameAndPassword(member.getName(), member.getPassword());
        if (findMember != null) {
            return ResponseEntity
                    .ok()
                    .body(jwtTokenProvider.createToken(String.valueOf(findMember.getMemberId()), Arrays.asList(findMember.getRoles().toString())));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("로그인 정보가 올바르지 않습니다.");
        }
    }

}
