package soongmile.soongmileback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing	// @CreatedDate와 @LastModifiedDate 사용하기 위해서
@SpringBootApplication
public class SoongmilebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoongmilebackApplication.class, args);
	}

}
