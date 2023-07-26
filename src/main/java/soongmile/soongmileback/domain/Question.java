package soongmile.soongmileback.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 제목
    @Column(length = 100, name = "title", nullable = false)
    private String title;

    // 본문
    @Column(name = "content", nullable = false)
    private String content;

    // 답변
    @OneToMany(mappedBy = "question", cascade = ALL)
    List<Answer> answers = new ArrayList<>();

    // 조회 수
    @Column(name = "hits")
    private int hits;

    // 게시 시간
    @Column(name = "post_time")
    // @CreatedDate
    private LocalDateTime postTime;

    // 수정 시간
    @Column(name = "edit_time")
    // @LastModifiedDate
    private LocalDateTime editTime;

    // 좋아요
    @Column(name = "good_count")
    private int goodCount;
}
