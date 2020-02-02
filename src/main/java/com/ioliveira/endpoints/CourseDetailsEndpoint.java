package com.ioliveira.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ioliveira.courses.CourseDetails;
import com.ioliveira.courses.GetCourseDetailsRequest;
import com.ioliveira.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {
	
	@PayloadRoot(namespace = "http://ioliveira.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse getCourse(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails cd = new CourseDetails();
		cd.setId(request.getId());
		cd.setName("Microservices with SOAP");
		cd.setDescription("Learn spring-boot with SOAP");
		response.setCourseDetails(cd);
		
		return response;
	}

}
