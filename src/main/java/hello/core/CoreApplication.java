package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {
//SpringBoot시 오버라이딩 사용 안함 -> 오버라이딩 사용시 application.properties에 spring.main.allow-bean-definition-overriding=true 추가
	
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
