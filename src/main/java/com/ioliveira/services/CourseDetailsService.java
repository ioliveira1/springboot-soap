package com.ioliveira.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ioliveira.beans.Course;

@Service
public class CourseDetailsService {
	
	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course c1 = Course.builder().id(1).name("Spring").description("10 steps").build();
		Course c2 = Course.builder().id(2).name("Spring MVC").description("10 examples").build();
		Course c3 = Course.builder().id(3).name("Spring Boot").description("6K students").build();
		Course c4 = Course.builder().id(4).name("Maven").description("Most popular course").build();
		
		courses.addAll(Arrays.asList(c1, c2, c3, c4));
	}
	
	public Course findById(int id) {
		Course course = courses
				.stream()
				.filter(c -> c.getId() == id)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Course id: " + id + " not found!"));
		return course;
	}
	
	public List<Course> findAll() {
		return courses;
	}
	
	public boolean deleteById(int id) {
		try {
			findById(id);
			return courses.removeIf(c -> c.getId() == id);
		} catch (Exception e) {
			System.out.println("Erro ao deletar o curso!");
		}
		return false;
	}
	
}
