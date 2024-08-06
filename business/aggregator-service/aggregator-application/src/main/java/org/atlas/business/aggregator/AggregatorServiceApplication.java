package org.atlas.business.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"org.atlas.business.aggregator",
	"org.atlas.framework"
})
public class AggregatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatorServiceApplication.class, args);
	}

}
