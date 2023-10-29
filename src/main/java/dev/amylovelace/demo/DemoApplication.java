package dev.amylovelace.demo;

import dev.amylovelace.demo.dao.DAO;
import dev.amylovelace.demo.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	private static DAO<Course> dao;

	public DemoApplication(DAO<Course> dao) {
		this.dao = dao;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("created course");
		Course javaCourse = new Course( " Java from zero to hero","Java from zero to hero","https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15434450?start=15#overview");
		dao.create(javaCourse);

		System.out.println("all courses");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);

		System.out.println("get one course");
		Optional<Course> courseGet = dao.get(73);
		System.out.println(courseGet.get());

		javaCourse.setDescription("Java de cero a experto");
		dao.update(javaCourse, 75);

		//dao.delete(25);

	}



}
