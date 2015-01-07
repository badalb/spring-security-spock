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


class BaseSpecification extends Specification {

    @Shared
    @AutoCleanup
    def restClient

    def setup() {
    	if(restClient == null){
     		restClient =  new RESTClient("http://localhost:8000")
    		login(restClient)
    	}
    }
    
    def cleanup() {
    	//restClient =  null
    } 
    
    def setupSpec() {} 
    
    def cleanupSpec() {
    	restClient =  null
    }
    
 def login(RESTClient restClient) {
			         
	restClient.post([path: "/login", contentType: 'application/x-www-form-urlencoded',
			         body: ["username=user&password=password"],
			         requestContentType: "application/x-www-form-urlencoded"])
			   }
			   
}