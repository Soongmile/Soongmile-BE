package soongmile.soongmileback.domain;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
@EntityListeners(AuditingEntityListener.class)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //answer_id

    // 답변 내용
    @Column(name = "content", length = 500, nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    @CreatedDate
    private LocalDateTime postTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private soongmile.soongmileback.domain.Question question;

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


    public static Answer create(AnswerCreateRequest request, soongmile.soongmileback.domain.Question question, Member member) {
        return Answer.builder()
                .content(request.getContent())
                .postTime(LocalDateTime.now())
                .likes(0)
                .member(member)
                .question(question)
                .build();
    }
}
