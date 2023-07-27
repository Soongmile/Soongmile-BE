package soongmile.soongmileback.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
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
    @Column(name = "content", nullable = false)
    private String content;

    // 게시 시간
    @Column(name = "post_time")
    // @CreatedDate
    private LocalDateTime postTime;

    // 수정 시간
    @Column(name = "edit_time")
    // @LastModifiedDate
    private LocalDateTime editTime;

    // 조회 수
    private int hits;

    // 좋아요
    private int likes;

    // 분야
    @Enumerated(STRING)
    private Field field;

    // 태그
    @Enumerated(STRING)
    private Tag tag;

    // 답변
    @OneToMany(mappedBy = "question", cascade = ALL)
    List<Answer> answers = new ArrayList<>();

    // 좋아요
    @Column(name = "good_count")
    private int goodCount;
}
