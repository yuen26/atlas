package org.atlas.edge.job.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
	"org.atlas.edge.job",
	"org.atlas.framework"
})
public class JobServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobServerApplication.class, args);
	}

}
