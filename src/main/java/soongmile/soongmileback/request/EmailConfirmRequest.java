package soongmile.soongmileback.request;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "email", timeToLive = 300)
public class EmailConfirmRequest {

    @Id
    private String email;
    private String code;

    public EmailConfirmRequest(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
