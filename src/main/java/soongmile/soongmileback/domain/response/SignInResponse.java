package soongmile.soongmileback.domain.response;

import lombok.Data;

@Data
public class SignInResponse {

    private String token;
    private String memberName;

    public SignInResponse(String token, String memberName) {
        this.token = token;
        this.memberName = memberName;
    }
}
