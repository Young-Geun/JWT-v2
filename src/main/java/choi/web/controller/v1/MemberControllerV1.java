package choi.web.controller.v1;

import choi.web.domain.Member;
import choi.web.jwt.JwtTokenProvider;
import choi.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    /**
     * 로그인
     */
    @PostMapping("/v1/login")
    public ResponseEntity login(@RequestBody Member member) {
        Member findMember = memberService.findMemberByName(member.getName());
        if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
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
