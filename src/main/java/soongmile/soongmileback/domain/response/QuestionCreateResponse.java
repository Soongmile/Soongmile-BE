
package soongmile.soongmileback.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
public class QuestionCreateResponse {

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @JsonProperty
    private List<Field> field;

    @JsonProperty
    private List<String> tag;

    @JsonProperty
    private LocalDateTime postTime;

    @JsonProperty
    private Long memberId;

    @JsonProperty
    private String memberName;

    @JsonProperty
    private Integer hits;

    @JsonProperty
    private Integer likes;

    @JsonProperty
    private List<AnswerView> answerList;

    @JsonProperty
    private List<String> imageUrls;

    @JsonProperty
    private Boolean isLikeMe;

}

