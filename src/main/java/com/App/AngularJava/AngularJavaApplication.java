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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class AngularJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngularJavaApplication.class, args);
	}



@Bean
ApplicationRunner init(CarRepository repository){
	return args -> {
		Stream.of("Ferrari", "Bugatti","Lamborghini","Aston Martin","Alpine","Porsche", "Pagani","Fiat","Tesla","Volvo","Renault").forEach(
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}

@RepositoryRestResource
interface CarRepository extends JpaRepository<Car, Long> {
}

@RestController
class carController {
	// inject dependency to get access to crud and jpa methods
	private CarRepository repository;
//Constructor
	public carController(CarRepository repository){
		 this.repository = repository;
	}

	//get
	@GetMapping("/allCars")
	public Collection<Car> beautifulCar(){
		return repository.findAll().stream().
				filter(this :: isCool)
				.collect(Collectors.toList());
	}

	public boolean isCool(Car car){
		return !car.getName().equals("Fiat") &&
		       !car.getName().equals("Volvo")&&
				!car.getName().equals("Renault");
	}

}
