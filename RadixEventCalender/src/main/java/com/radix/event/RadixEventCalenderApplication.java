package com.radix.event;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadixEventCalenderApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(RadixEventCalenderApplication.class, args);
		openHomePage();
	}
	
	private static void openHomePage() throws IOException 
	{
		 Runtime rt = Runtime.getRuntime();
	     rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:9099/event/loadEventCalender");
		 //rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:9090/university/cmsEnroll/loadPersonalDetailsPage");
	}

}
