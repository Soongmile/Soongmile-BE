package soongmile.soongmileback.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class AnswerCreateRequest {

    @Min(1)
    @JsonProperty
    private Long questionId;

    @NotEmpty(message = "본문을 입력해주세요.")
    @Size(max = 800, message = "제목은 최대 800글자까지 입력할 수 있습니다.")
    @JsonProperty
    private String content;

}

