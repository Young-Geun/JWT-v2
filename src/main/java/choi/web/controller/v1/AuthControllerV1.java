package choi.web.controller.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerV1 {

    /**
     * 회원 권한 접근
     */
    @PostMapping("/v1/user/access")
    public ResponseEntity accessUser() {
        return ResponseEntity
                .ok()
                .body("Hello User World!");
    }

    /**
     * 관리자 권한 접근
     */
    @PostMapping("/v1/admin/access")
    public ResponseEntity accessAdmin() {
        return ResponseEntity
                .ok()
                .body("Hello Admin World!");
    }

}
