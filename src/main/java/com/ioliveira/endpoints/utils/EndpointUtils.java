package com.ioliveira.endpoints.utils;

import com.ioliveira.beans.Course;
import com.ioliveira.courses.CourseDetails;

public class EndpointUtils {

    public static CourseDetails convertToCourseDetails (Course course){

        CourseDetails cd = new CourseDetails();
        cd.setId(course.getId());
        cd.setName(course.getName());
        cd.setDescription(course.getDescription());

        return cd;
    }

}
