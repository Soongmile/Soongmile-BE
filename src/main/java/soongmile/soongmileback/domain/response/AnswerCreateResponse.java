
package soongmile.soongmileback.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Field;

import java.util.List;

@Getter @Setter
@Builder
public class AnswerCreateResponse {

    @JsonProperty
    private String content;

    @JsonProperty
    private Long memberId;

    @JsonProperty
    private String memberName;

    @JsonProperty
    private Long questionId;

    @JsonProperty
    private Integer likes;

    @JsonProperty
    private List<String> imageUrls;

}

