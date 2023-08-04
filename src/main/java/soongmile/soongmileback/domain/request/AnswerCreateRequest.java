
package soongmile.soongmileback.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class AnswerCreateRequest {

    @NotEmpty(message = "본문을 입력해주세요.")
    @Size(max = 800, message = "제목은 최대 500글자까지 입력할 수 있습니다.")
    @JsonProperty
    private String content;

}

