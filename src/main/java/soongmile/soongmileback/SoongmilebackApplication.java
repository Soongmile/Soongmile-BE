package soongmile.soongmileback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing	// @CreatedDate와 @LastModifiedDate 사용하기 위해서
@SpringBootApplication
public class SoongmilebackApplication {

	@PostConstruct
	void started() {
		// timezone UTC 셋팅
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SoongmilebackApplication.class, args);
	}

}
