package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (
			StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {

			Address address = new Address("Ivory coast",
					"Abidjan", "NE9");

			String email= "lagonoura2@gmail.com";
			Student student = new Student(
					"Maeva",
					"Lago",
					email,
					Gender.Female,
					address,
					List.of("Computer Science", "Maths"),
					BigDecimal.TEN,
					LocalDate.now()



			);


			// Query that searches emails
			// find a student with a specific email
			// throw an error if this email is attached to many students

			repository.findStudentByEmail(email).ifPresentOrElse(s ->{
				System.out.println(student + "already exists");
			}, () -> {});
		};
	}


	// UsingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
	private void UsingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();

		// Query that searches emails
		query.addCriteria(Criteria.where("email").is(email));

		// find a student with a specific email
		List<Student> students = mongoTemplate.find(query, Student.class);

		// throw an error if this email is attached to many students
		if (students.size() > 1) {
			throw new IllegalStateException(
					"found many students with email" + email);
		}

		//Check if no students in the list, if so insert one, otherwise print that the student already exists.
		if (students.isEmpty()) {
			System.out.println("Inserting student" + student);
			repository.insert(student);
		} else {
			System.out.println(student + "already exists");
		}
	}
}


