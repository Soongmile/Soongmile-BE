
package soongmile.soongmileback.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Field;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TagUpdateRequest {

    @NotEmpty(message = "태그 이름을 선택해주세요.")
    @JsonProperty
    private String tagName;

}

