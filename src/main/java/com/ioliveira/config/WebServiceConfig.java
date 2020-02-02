package com.ioliveira.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
	
	@Bean
	public ServletRegistrationBean<?> messageDispatcher(ApplicationContext context) {
		MessageDispatcherServlet mds = new MessageDispatcherServlet();
		
		mds.setApplicationContext(context);
		mds.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<>(mds, "/ws/*");
	}
	
	// /ws/courses.wsdl -> URL do wsdl
	@Bean(name = "courses")
	public DefaultWsdl11Definition wsdlDefinition(XsdSchema schema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://ioliveira.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(schema);
		
		return definition;
	}
	
	@Bean
	public XsdSchema courseSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

}
