package br.com.reserva;

import br.com.reserva.reserve.domain.Reserve;
import br.com.reserva.reserve.repository.ReserveRepository;
import br.com.reserva.user.domain.User;
import br.com.reserva.user.repository.UserRepository;

import java.time.LocalDate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservaApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReserveRepository reserveRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReservaApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		User user = new User();
		user.setName("Matheus");
		user.setCpf("01446294102");
		
		Reserve reserve = new Reserve();
		reserve.setBooking_date(LocalDate.of(2021,02,01));
		
		userRepository.save(user);
		reserve.setUser(user);
		reserveRepository.save(reserve);
		return null;
	}

}
