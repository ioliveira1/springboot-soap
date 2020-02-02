package com.ioliveira.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ioliveira.beans.Course;
import com.ioliveira.courses.CourseDetails;
import com.ioliveira.courses.GetCourseDetailsRequest;
import com.ioliveira.courses.GetCourseDetailsResponse;
import com.ioliveira.services.CourseDetailsService;

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

}
