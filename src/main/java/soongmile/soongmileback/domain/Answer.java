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
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
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
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 본문
    @Column(name = "content", length = 500, nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    @CreatedDate
    private LocalDateTime postTime;

    // 수정 시간
//    @Column(name = "edit_time")
//    @LastModifiedDate
//    private LocalDateTime editTime;


    // 좋아요
    @Column(name = "likes", nullable = false)
    private Integer likes;


    // TODO: 2023/07/31 Answer 개발이후
    // 답변
//    @OneToMany(mappedBy = "question", cascade = ALL, fetch = LAZY)
//    List<Answer> answers = new ArrayList<>();

    public static Answer create(AnswerCreateRequest request) {
        // TODO: 2023/07/31 현재 멤버 기능이 없어서 임시로 멤버 생성
        Member member = new Member();
        member.setId(1L);
        return Answer.builder()
                .content(request.getContent())
                .member(member)
                .likes(0)
                .build();
    }
}
