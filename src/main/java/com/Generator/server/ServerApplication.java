package com.Generator.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		/*ApplicationContext ctx = */SpringApplication.run(ServerApplication.class, args);
//		BlockedSitesRepository repository = ctx.getBean(BlockedSitesRepository.class);
//		repository.save(new BlockedSite("https://telegram.org/"));
//		repository.save(new BlockedSite("https://azure.microsoft.com/"));
	}
}