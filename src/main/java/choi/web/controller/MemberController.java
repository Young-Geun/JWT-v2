package choi.web.controller;

import choi.web.domain.Member;
import choi.web.jwt.JwtTokenProvider;
import choi.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    // TODO: 2/14/23 - 응답 값 정규화

    /**
     * 회원 등록
     */
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
        return ResponseEntity
                .ok()
                .body(member);
    }

    /**
     * 회원 전체 조회
     */
    @GetMapping("/members")
    public ResponseEntity findMember() {
        return ResponseEntity
                .ok()
                .body(memberService.findAllMember());
    }

    /**
     * 회원 조회
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity findMember(@PathVariable("memberId") Long id) {
        return ResponseEntity
                .ok()
                .body(memberService.findMember(id));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Member member) {
        if (memberService.findMemberByNameAndPassword(member.getName(), member.getPassword()).isPresent()) {
            return ResponseEntity
                    .ok()
                    .body(jwtTokenProvider.createToken(String.valueOf(member.getMemberId())));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("로그인 정보가 올바르지 않습니다.");
        }
    }

}
