package org.gox.bsdata.wh40k;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BsdataWh40kApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsdataWh40kApiApplication.class, args);
	}
}
