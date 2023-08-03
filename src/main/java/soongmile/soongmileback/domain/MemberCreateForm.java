package soongmile.soongmileback.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberCreateForm {

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email // 해당 속성의 값이 이메일 형식과 일치하는 지를 검증
    @JsonProperty
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @JsonProperty
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    @JsonProperty
    private String passwordchecker;

    @Size(min = 2, max = 10)
    @NotEmpty(message = "닉네임은 필수항목입니다.")
    @JsonProperty
    private String membername;
}
