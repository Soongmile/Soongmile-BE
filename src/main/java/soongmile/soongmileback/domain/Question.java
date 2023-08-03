package soongmile.soongmileback.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.Tag;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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

    // TODO: 2023/07/31 Answer 개발이후
    // 답변
//    @OneToMany(mappedBy = "question", cascade = ALL, fetch = LAZY)
//    List<Answer> answers = new ArrayList<>();

    public static Question create(QuestionCreateRequest request) {
        // TODO: 2023/07/31 현재 멤버 기능이 없어서 임시로 멤버 생성  
        Member member = new Member();
        member.setId(1L);
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
