package soongmile.soongmileback.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 본문
    @Column(name = "content", nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    // @CreatedDate
    private LocalDateTime postTime;

    @ManyToOne(fetch = LAZY)
    private Question question;

    @ManyToOne(fetch = LAZY)
    private Member member;
}
