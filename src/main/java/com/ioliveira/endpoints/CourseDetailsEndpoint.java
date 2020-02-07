package com.ioliveira.endpoints;

import com.ioliveira.beans.Course;
import com.ioliveira.courses.*;
import com.ioliveira.endpoints.utils.EndpointUtils;
import com.ioliveira.services.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	private CourseDetailsService courseDetailsService;
	
	@PayloadRoot(namespace = "http://ioliveira.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse getCourse(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		Course course = courseDetailsService.findById(request.getId());
		
		CourseDetails cd = new CourseDetails();
		cd.setId(course.getId());
		cd.setName(course.getName());
		cd.setDescription(course.getDescription());
		response.setCourseDetails(cd);
		
		return response;
	}

	@PayloadRoot(namespace = "http://ioliveira.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse getAllCourses(){
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		List<Course> courseList = courseDetailsService.findAll();

		List<CourseDetails> courseDetailsList =
				courseList
				.stream()
				.map(EndpointUtils::convertToCourseDetails)
				.collect(Collectors.toList());

		response.getCourseDetails().addAll(courseDetailsList);

		return response;
	}

	@PayloadRoot(namespace = "http://ioliveira.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourse (@RequestPayload DeleteCourseDetailsRequest request){
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();

		response.setStatus(courseDetailsService.deleteById(request.getId()));

		return response;
	}

}
