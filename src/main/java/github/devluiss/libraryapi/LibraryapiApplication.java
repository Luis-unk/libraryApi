package github.devluiss.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryapiApplication {
	public static void main(String[] args) { SpringApplication.run(LibraryapiApplication.class, args);
	}
}