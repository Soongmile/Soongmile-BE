package soongmile.soongmileback.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 아이디
    private String username;

    // 비밀번호
    private String password;

    // 닉네임
    private String nickname;

    // 학과
    @Enumerated(EnumType.STRING)
    private Major major;

    // 학번
    @Column(name = "college_id")
    private String collegeId;

    // 작성한 질문글 모음
    @OneToMany(mappedBy = "user")
    private List<Question> questions = new ArrayList<>();

    // 작성한 답변 모음
    @OneToMany(mappedBy = "user")
    private List<Answer> answers = new ArrayList<>();

    // 포인트
    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "point_id")
    private Point point;

    protected User() {

    }

    @Builder
    public User(String username, String password, String nickname, Major major, String collegeId) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.major = major;
        this.collegeId = collegeId;
        this.point = new Point();
    }
}
