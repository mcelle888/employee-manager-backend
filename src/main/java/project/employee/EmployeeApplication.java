package project.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class EmployeeApplication {
	private static final Logger logger = LogManager.getLogger(EmployeeApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Employee Management App");
		SpringApplication.run(EmployeeApplication.class, args);
	}
}
