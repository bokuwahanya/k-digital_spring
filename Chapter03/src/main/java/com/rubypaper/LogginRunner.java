package com.rubypaper;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogginRunner implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		log.trace("Trace 로그 메세지");
		log.debug("dubug 로그 메세지");
		log.info("info 로그 메세지");
		log.warn("Warn 로그 메세지");
		log.error("error 로그 메세지");
	}
}
