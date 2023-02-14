package choi.web.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private MEMBER_ROLES roles;

}
