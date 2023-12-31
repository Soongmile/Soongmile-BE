
package soongmile.soongmileback.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import reactor.util.annotation.Nullable;
import soongmile.soongmileback.domain.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
public class QuestionCreateRequest {

    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 100, message = "제목은 최대 100글자까지 입력할 수 있습니다.")
    @JsonProperty
    private String title;

    @NotEmpty(message = "본문을 입력해주세요.")
    @Size(max = 500, message = "제목은 최대 500글자까지 입력할 수 있습니다.")
    @JsonProperty
    private String content;

    @NotEmpty(message = "분야를 선택해주세요.")
    @JsonProperty
    private List<Field> field;

    @NotEmpty(message = "하나 이상 태그를 입력해주세요.")
    @JsonProperty
    private List<String> tag;

    @JsonProperty
    @Nullable
    private List<Long> fileIds;

}

