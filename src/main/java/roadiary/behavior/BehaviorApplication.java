package roadiary.behavior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import roadiary.behavior.member.service.token.KakaoToken;
import roadiary.behavior.member.service.token.KakaoTokenForLocal;
import roadiary.behavior.member.service.token.KakaoTokenForProd;

@SpringBootApplication
public class BehaviorApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BehaviorApplication.class, args);
		SpringApplication app = new SpringApplication(BehaviorApplication.class);
		app.setAdditionalProfiles("local");
		app.run();
	}

    @Bean
    @Profile("local")
    KakaoToken kakaoTokenForLocal() {
		return new KakaoTokenForLocal();
	}

	@Bean
    @Profile("prod")
    KakaoToken kakaoTokenForProd() {
		return new KakaoTokenForProd();
	}

}
