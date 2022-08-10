package snapshot.behavior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BehaviorApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BehaviorApplication.class, args);
		SpringApplication app = new SpringApplication(BehaviorApplication.class);
		app.setAdditionalProfiles("local");
		app.run();
	}

}
