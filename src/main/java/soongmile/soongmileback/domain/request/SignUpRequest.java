package soongmile.soongmileback.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email // 해당 속성의 값이 이메일 형식과 일치하는 지를 검증
    @JsonProperty
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @JsonProperty
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수항목입니다.")
    @JsonProperty
    private String passwordchecker;

    @Size(min = 2, max = 10)
    @NotEmpty(message = "닉네임은 필수항목입니다.")
    @JsonProperty
    private String membername;
}
