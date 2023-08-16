
package soongmile.soongmileback.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import soongmile.soongmileback.domain.Answer;
import soongmile.soongmileback.domain.Field;

import java.util.List;

@Getter @Setter
@Builder
public class QuestionCreateResponse {

    @JsonProperty
    private String title;

    @JsonProperty
    private String content;

    @JsonProperty
    private Field field;

    @JsonProperty
    private String tag;

    @JsonProperty
    private Long memberId;

    @JsonProperty
    private Integer hits;

    @JsonProperty
    private Integer likes;

    @JsonProperty
    private List<AnswerView> answerList;

}

