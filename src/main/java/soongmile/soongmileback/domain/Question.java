package soongmile.soongmileback.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY -> AUTO
    private Long id;

    // 작성자
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //user_id

    // 제목
    @Column(length = 100, name = "title", nullable = false)
    private String title;

    // 본문
    @Column(name = "content", length = 500, nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    @CreatedDate
    private LocalDateTime postTime;

    // 수정 시간
    @Column(name = "edit_time")
    @LastModifiedDate
    private LocalDateTime editTime;

    // 조회 수
    @Column(name = "hits", nullable = false)
    private Integer hits;

    // 좋아요
    @Column(name = "likes", nullable = false)
    private Integer likes;

    // 분야
    @Enumerated(STRING)
    @Column(name = "field", nullable = false)
    private Field field;

    // 태그
    @Column(name = "tag", nullable = false)
    private String tag;

    // 답변
    @OneToMany(mappedBy = "question", cascade = ALL, fetch = LAZY)
    List<Answer> answers = new ArrayList<>();

    public static Question create(QuestionCreateRequest request, Member member) {
        return Question.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .field(request.getField())
                .tag(request.getTag())
                .member(member)
                .hits(0)
                .likes(0)
                .build();
    }

}
