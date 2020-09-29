package com.App.AngularJava;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

@SpringBootApplication
public class AngularJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularJavaApplication.class, args);
	}



@Bean
ApplicationRunner init(CarRepository repository){
	return args -> {
		Stream.of("Ferrari", "Bugatti","Lamborghini","Aston Martin","Alpine","Porsche", "Pagani").forEach(
				name ->{repository.save(new Car(name));
				});
		for (Car car : repository.findAll()) {
			System.out.println(car);
		}
	};

  }
}


@Data
@NoArgsConstructor
@Entity
class Car{
	public Car(String name){
		this.name = name;
	}

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String name;
}

@RepositoryRestResource
interface CarRepository extends JpaRepository<Car, Long> {
}