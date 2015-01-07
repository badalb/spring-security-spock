package com.ssecurity

import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.boot.test.SpringApplicationContextLoader
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.boot.test.IntegrationTest
import org.springframework.beans.factory.annotation.Value

import groovyx.net.http.RESTClient
import com.ssecurity.BaseSpecification


@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = [Application.class])
@WebAppConfiguration
@IntegrationTest("server.port:8000")

@Stepwise
class HelloControllerSpec extends BaseSpecification {

    
  void "should return Greetings from Spring Boot!"() {
    	
        when:
       
       def response = restClient.get([path: "", contentType: 'text/html',
			         requestContentType: "text/html"])
       
        then:
     
        response.data == 'Greetings from Spring Boot!'
    }

 void "should reverse request!"() {
 
        when:
        
        def response = restClient.get([path: "/reverse/$url", contentType: 'text/html',
			         requestContentType: "text/html"])

        then:
        response.data == reversedString

        where:
        url   || reversedString
        'uno' || 'onu'
        'ufc' || 'cfu'
    }

}