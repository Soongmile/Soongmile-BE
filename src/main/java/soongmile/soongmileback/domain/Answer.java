package soongmile.soongmileback.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @Column(name = "answer_id")
    private Long id;    //answer_id

    // 답변 내용
    @Column(name = "content", length = 500, nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    @CreatedDate
    private LocalDateTime postTime;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    // 작성자
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //user_id

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
        // TODO: 2023/08/05 현재 멤버 기능이 없어서 임시로 멤버 생성
        Member member = new Member();
        Question question = new Question();
        member.setId(1L);
        return Answer.builder()
                .content(request.getContent())
                .likes(0)
                .member(member)
                .question(question)
                .build();
    }
}
