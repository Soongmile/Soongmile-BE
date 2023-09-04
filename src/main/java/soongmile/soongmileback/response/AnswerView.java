
package soongmile.soongmileback.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Answer;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class AnswerView {

    private Long id;    //answer_id

    // 답변 내용
    private String content;

    // 게시 시간
    private LocalDateTime postTime;

    private Long memberId; //user_id

    // 좋아요
    private Integer likes;

    public static AnswerView create(Answer request) {
        return AnswerView.builder()
                .id(request.getId())
                .content(request.getContent())
                .postTime(request.getPostTime())
                .memberId(request.getMember().getId())
                .likes(request.getLikes())
                .build();
    }

}

