
package soongmile.soongmileback.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soongmile.soongmileback.domain.Field;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@Builder
public class TagView {

    @JsonProperty
    private Long id;

    @JsonProperty
    private Field field;

    @JsonProperty
    private String tagName;

}

