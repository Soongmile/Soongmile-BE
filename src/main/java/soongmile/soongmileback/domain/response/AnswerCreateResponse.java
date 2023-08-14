
package soongmile.soongmileback.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AnswerCreateResponse {

    @JsonProperty
    private String content;

    @JsonProperty
    private Long memberId;

    @JsonProperty
    private Long questionId;

}

