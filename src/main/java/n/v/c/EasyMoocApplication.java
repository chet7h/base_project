package n.v.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EasyMoocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyMoocApplication.class, args);
	}

}
