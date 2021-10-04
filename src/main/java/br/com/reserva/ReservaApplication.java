package br.com.reserva;

import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservaApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReservaApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		User user = new User();
		user.setName("Matheus");
		user.setEmail("matheus.encode@gmail.com");
		userRepository.save(user);


		return null;
	}

}
