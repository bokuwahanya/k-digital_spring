package com.rubypaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rubypaper"})
//패키지를 찾아준다 기본 패키지 안에서는 필요없다.
public class Mission3Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission3Application.class, args);
	}

}
