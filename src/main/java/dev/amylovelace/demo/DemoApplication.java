package dev.amylovelace.demo;

import dev.amylovelace.demo.dao.DAO;
import dev.amylovelace.demo.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private static DAO<Course> dao;

	public DemoApplication(DAO<Course> dao) {
		this.dao = dao;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("all courses");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);
	}



}
