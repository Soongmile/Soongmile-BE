package soongmile.soongmileback.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginReq {

    private String email;
    private String password;
}
