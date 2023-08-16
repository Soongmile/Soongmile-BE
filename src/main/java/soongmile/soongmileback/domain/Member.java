package soongmile.soongmileback.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import soongmile.soongmileback.constant.Constant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 이메일
    @Column(unique = true, nullable = false)
    private String email;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 닉네임
    @Column(name = "member_name", unique = true, nullable = false)
    private String memberName;

    // 소속 대학
    @Enumerated(STRING)
    private Department department;

    // 학과
    @Enumerated(STRING)
    private Major major;

    // 분야
    @Enumerated(STRING)
    private Field field;

    // 학번
    @Column(name = "college_id")
    private String collegeId;

    // 작성한 질문글 모음
    @OneToMany(mappedBy = "member", cascade = ALL, fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    // 작성한 답변 모음
    @OneToMany(mappedBy = "member", cascade = ALL, fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    // 포인트
    private int point;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Builder
    public Member(String email, String password, String memberName) {
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.department = null;
        this.major = null;
        this.field = null;
        this.collegeId = null;
        this.point = Constant.CREATE_ACCOUNT;
        this.roles = Collections.singletonList("ROLE_USER");
    }

    public void addQuestionPoint() {
        this.point += Constant.CREATE_QUESTION;
    }

    public void addAnswerPoint() {
        this.point += Constant.CREATE_ANSWER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
